package org.acme.movie;

import io.quarkus.panache.common.Sort;
import org.acme.data.Movie;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {
    @GET
    @Path("/list")
    public List<Movie> getMovies() {

        return Movie.listAll(Sort.ascending("title"));
    }

    @POST
    @Path("/add")
    @Transactional
    public void addMovie(Movie movie) {
        Movie.persist(movie);
    }

    @GET
    @Path("/get/{id}")
    public Movie getMovie(int id) {
        return Movie.findById(id);
    }
}
