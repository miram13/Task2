package org.acme.booking;

import io.quarkus.panache.common.Sort;

import org.acme.data.Booking;
import org.acme.data.Movie;
import org.acme.data.Seat;
import org.acme.data.User;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import java.util.List;

@Path("/ticketbooking")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {
    @GET
    @Path("/list")
    public List<Booking> getBooking() {

        return Booking.findAll().list();
    }

    @POST
    @Path("/book/{movie_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void bookTicket(@Context SecurityContext ct, @PathParam("movie_id") Long movie_id,
                           String[] seat_numbers) {
        Movie movie = Movie.findById(movie_id);
        String uname = ct.getUserPrincipal().getName();
        User user = User.find("email", uname).firstResult();
        for (String seat_number : seat_numbers) {
            Booking booking = new Booking();
            booking.setUser(user);
            booking.setMovie(movie);
            booking.setTitle(movie.getTitle());
            booking.setFirstName(user.getFirstName());
            booking.setSeatNumber(seat_number);
            Booking.persist(booking);
        }

    }

}
