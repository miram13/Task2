package org.acme.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Booking extends PanacheEntity {
    @OneToOne
    Movie movie;
    String seat_number;
    @OneToOne
    User user;

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setSeatNumber(String seat_number) {
        this.seat_number = seat_number;
    }

    public String getSeatNumber() {
        return seat_number;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
