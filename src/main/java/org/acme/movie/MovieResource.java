package org.acme.movie;

import io.quarkus.panache.common.Sort;

import org.acme.data.Booking;
import org.acme.data.Movie;
import org.acme.data.Seat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/movies")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    EntityManager em;

    @GET
    @Path("/list")
    public List<Movie> getMovies() {

        return Movie.listAll(Sort.ascending("title"));
    }

    @GET
    @Path("/seatslist")
    public List<Seat> getSeats() {
        List<Seat> seats = Seat.findAll().list();
        for (Seat seat : seats) {
            TypedQuery<Booking> q = em.createQuery("select b from Booking b where seat_number = (:seatNumber)",
                    Booking.class);
            q.setParameter("seatNumber", seat.getSeatNumber());
            List<Booking> b = q.getResultList();
            if (b.size() >= 1) {
                seat.setSeatStatus("reserved");
            } else {
                seat.setSeatStatus("empty");
            }

        }
        return Seat.findAll().list();
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
