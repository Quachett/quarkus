package uk.co.argon.backend;

import java.sql.SQLException;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import uk.co.argon.backend.dao.ArtistFacadeDao;
import uk.co.argon.cdm.orm.Artist;

@ApplicationScoped
public class ArtistFacadeBean implements ArtistFacade {

	@Inject
	private ArtistFacadeDao dao;
	
	@Override
	public Artist getArtistById(int id) throws SQLException {
		return dao.getArtistById(id);
	}

	@Override
	public String saveArtist(List<Artist> artists) throws SQLException {
		return dao.saveArtist(artists);
	}

}
