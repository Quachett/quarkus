package uk.co.argon.backend.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.co.argon.cdm.orm.Artist;
import uk.co.inc.argon.commons.util.CommonsUtil;
import uk.co.inc.argon.commons.util.DateUtil;

public class ArtistTest {

	public static void main(String[] args) {
		getArtist();
	}
	
	private static void getArtist() {
		Artist artist = null;
		List<Artist> la = new ArrayList<Artist>();
		DateUtil du = new DateUtil();
		
		artist = new Artist();
		artist.setBio("Was a Jamaican singer, musician, and songwriter. Considered one of the pioneers of reggae, his musical career was marked by fusing elements of reggae, ska, and rocksteady, as well as his distinctive vocal and songwriting style.");
		artist.setCreatedDate(du.formatDate(new Date()));
		artist.setName("Bob Marley");
		la.add(artist);
		
		artist = new Artist();
		artist.setBio("Was a Jamaican singer, musician, and songwriter. Considered one of the pioneers of reggae, his musical career was marked by fusing elements of reggae, ska, and rocksteady, as well as his distinctive vocal and songwriting style.");
		artist.setCreatedDate(du.formatDate(new Date()));
		artist.setName("Lloyd Mukwacha");
		la.add(artist);
		
		System.out.println(CommonsUtil.getGson().toJson(la));
	}

}
