package uk.co.argon.backend.dao;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import uk.co.argon.cdm.orm.Artist;

@ApplicationScoped
public interface ArtistFacadeDao {
	public Artist getArtistById(int id) throws SQLException;
	public String saveArtist(List<Artist> artists) throws SQLException;
}
