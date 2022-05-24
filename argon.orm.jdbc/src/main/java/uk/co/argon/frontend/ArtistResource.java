package uk.co.argon.frontend;

import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import uk.co.argon.backend.ArtistFacade;
import uk.co.argon.cdm.Artist;

@Path("/artist")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class ArtistResource {

    @Inject
    private ArtistFacade af;
    
    @GET
    @Path("{id}")
    public Response getArtistNyId(@PathParam("id") int id) {
    	try {
    		System.out.println("Get Artist by ID");
    		Artist response = af.getArtistById(id);
    		if(response != null)
    			return Response.status(Status.OK).entity(response).build();
    		
    		return Response.status(Status.NO_CONTENT).entity(new Artist()).build();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
    }
    
    @POST
    public Response addArtists(List<Artist> artists) {
    	try {
    		System.out.println("Add Artist");
    		String response = af.saveArtist(artists);
    		
    		return Response.status(Status.OK).entity(response).build();
    	} catch (Exception e) {
    		e.printStackTrace();
    		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
			// TODO: handle exception
		}
    }
}