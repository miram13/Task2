package org.acme.seat;

import io.quarkus.panache.common.Sort;

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
public class SeatResource {

    @Inject
    EntityManager em;

    @GET
    @Path("/list")
    public List<Seat> getSeats() {

        return Seat.listAll(Sort.ascending("seat_number"));
    }

    @POST
    @Path("/add")
    @Transactional
    public void addSeat(Seat seat) {
        Seat.persist(seat);
    }

}
