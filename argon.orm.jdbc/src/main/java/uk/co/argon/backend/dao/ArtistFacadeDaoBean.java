package uk.co.argon.backend.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.quarkus.agroal.DataSource;
import uk.co.argon.cdm.orm.Artist;
import uk.co.inc.argon.commons.util.DateUtil;

@ApplicationScoped
public class ArtistFacadeDaoBean implements ArtistFacadeDao {
	
	@Inject
	@DataSource("oracleDs")
	javax.sql.DataSource oracleDS;

	@Override
	public Artist getArtistById(int id) throws SQLException {
		String query = "SELECT * FROM synapse.t_artist WHERE id = ?";
		return getArtistById(query, id);
	}

	@Override
	public String saveArtist(List<Artist> artists) throws SQLException {
		String query = "INSERT INTO synapse.t_artist (name, bio, created_date) VALUES(?,?,?)";
		saveArtist(artists, query);
		return "{\"message\": \"Artists Saved\"}";
	}

	private void saveArtist(List<Artist> artists, String query) throws SQLException {
		try(Connection conn = oracleDS.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
			for(Artist a: artists) {
				ps.setString(1, a.getName());
				ps.setString(2, a.getBio());
				ps.setTimestamp(3, Timestamp.valueOf(a.getCreatedDate()));
				ps.addBatch();
			}
			ps.executeBatch();
		}
	}

	private Artist getArtistById(String query, int id) throws SQLException {
		Artist artist = null;
		DateUtil du = new DateUtil();
		
		try(Connection conn = oracleDS.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);){
			ps.setInt(1, id);
			
			try(ResultSet rs = ps.executeQuery()){
				if(rs.next()) {
					artist = new Artist();
					artist.setBio(rs.getString("BIO"));
					artist.setCreatedDate(du.formatDateTimeMilli(rs.getTimestamp("CREATED_DATE")));
					artist.setId(rs.getInt("ID"));
					artist.setName(rs.getString("NAME"));
				}
			}
		}
		
		//System.out.println(CommonsUtil.getSerialisedObj(artist));
		return artist;
	}

}
