package org.acme.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Notify extends PanacheEntity {

    public Notify(User user, Movie movie) {
        this.user = user;
        this.movie = movie;



    }

    @OneToOne
    private User user;
    @OneToOne
    private Movie movie;

    private String firstName;
    private String lastName;
    private String title;
    private String seats;

    public Notify() {

    }
    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getSeats() {
        return seats;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setLastName(String lastNameName) {
        this.lastName = lastNameName;
    }

    public String getLastName() {
        return lastName;
    }
}