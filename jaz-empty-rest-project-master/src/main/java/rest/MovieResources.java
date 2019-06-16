package rest;

import domain.Comment;
import domain.Movie;
import domain.services.MovieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@Path("/movies")
public class MovieResources {
    private MovieService db = new MovieService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAll(){
        return db.getAll();
    }

    @GET
    @Path("/{movieId}")
    @Produces (MediaType.APPLICATION_JSON)
    public Response get(@PathParam("movieId") int movieId){
        Movie result =db.get(movieId);
        if(result==null){
            return Response.status(404).build();
        }
        return Response.ok(result).build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add (Movie movie){
        db.add(movie);
        return Response.ok(movie.getId()).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("movieId") int movieId, Movie m){
        Movie result = db.get(movieId);
        if(result==null){
            return Response.status(404).build();
        }
        m.setId(movieId);
        db.update(m);
        return Response.ok(result).build();
    }

    @GET
    @Path("/{movieId}/comment")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comment> getComments(@PathParam("movieId") int movieId){
        Movie result = db.get(movieId);
        if(result==null){
            return null;
        }
        if (result.getComments()==null){
            result.setComments(new ArrayList<Comment>());
        }
        return result.getComments();
    }

    @POST
    @Path("/{movieId}/comment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComment(@PathParam("movieId")int movieId, Comment comment){
        Movie result= db.get(movieId);
        if(result==null){
            return Response.status(404).build();
        }
        if(result.getComments()==null){
            result.setComments(new ArrayList<Comment>());
        }
        result.getComments().add(comment);
        comment.setId(result.getComments().indexOf(comment));
        return Response.ok(comment.getId()).build();

    }

    @DELETE
    @Path("/{movieId}/comment/{commentId}")
    public Response deleteComment (@PathParam("movieId") int movieId, @PathParam("commentId") int commentId){
        Movie result=db.get(movieId);
        if(result.getComments()==null){
            return Response.status(404).build();
        }
        result.getComments().remove(commentId);
        return Response.ok().build();
    }

}
