package org.acme.data;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Seat extends PanacheEntity {

    private String seat_number;

    private String seat_status;

    public void setSeatNumber(String seat_number) {
        this.seat_number = seat_number;
    }

    public String getSeatNumber() {
        return seat_number;
    }

    public void setSeatStatus(String seat_status) {
        this.seat_status = seat_status;
    }

    public String getSeatStatus() {
        return seat_status;
    }
}