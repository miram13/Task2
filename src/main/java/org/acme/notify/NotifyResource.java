package org.acme.notify;

import org.acme.data.Notify;
import org.acme.data.Movie;
import org.acme.data.User;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path("/Notify")
public class NotifyResource {

    @Inject
    JsonWebToken jwt;

    @Claim(standard = Claims.preferred_username)
    String username;

    @POST
    @Path("/notify/{movie_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public void addItemstoNotify(@Context SecurityContext ct, @PathParam("movie_id") Long movie_id) {
        System.out.println("pid:" + movie_id);
        Notify notify = new Notify();
        Movie movie = Movie.findById(movie_id);
        System.out.println("pname:" + movie.getTitle());
        String uname = ct.getUserPrincipal().getName();
        User user = User.find("email", uname).firstResult();
        notify.setUser(user);
        notify.setMovie(movie);
        notify.setSeats(notify.getSeats());
        notify.setTitle(movie.getTitle());
        notify.setFirstName(user.getFirstName());
        notify.setLastName(user.getLastName());
        notify.persist(notify);
    }

    @GET
    @Path("/notify/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Notify findNotifyById(@PathParam("id") long id) {
        return Notify.findById(id);
    }

    @GET
    @Path("/notify")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Notify> getAllNotify() {
        return Notify.findAll().list();
    }

    @GET
    @Path("/notify/items")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<Notify> getUserNotifyItems(@Context SecurityContext ct) {
        String uname = ct.getUserPrincipal().getName();
        User user = User.find("email", uname).firstResult();
        return Notify.find("user_id", user.id).list();
    }

    @DELETE
    @Path("/notify")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAllCart() {
        Notify.deleteAll();
    }

//

    @DELETE
    @Path("/notify/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public boolean deleteNotify(@PathParam("id") long id) {
        System.out.println("cid:" + id);
        return Notify.deleteById(id);
    }
}
