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

    private String title;

    public Notify() {

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

    public void setProduct(Movie movie) {
        this.movie = movie;
    }



}