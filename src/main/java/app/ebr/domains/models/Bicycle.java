package app.ebr.domains.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import app.ebr.domains.enums.BicycleType;

@Entity
@Table(name = "bicycle")
public class Bicycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    // @Column(name = "using")
    // private boolean using;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "bicycle_type")
    private BicycleType bicycleType;

    @Column(name = "time_started")
    private Date timeStarted;

    public Bicycle() {

    }

    public Bicycle(BicycleType bicycleType) {
        this.bicycleType = bicycleType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    // public void setUsing(boolean using) {
    // this.using = using;
    // }

    // public boolean isUsing() {
    // return using;
    // }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setTimeStarted(Date timeStarted) {
        this.timeStarted = timeStarted;
    }

    public Date getTimeStarted() {
        return timeStarted;
    }

    public void setBicycleType(BicycleType bicycleType) {
        this.bicycleType = bicycleType;
    }

    public BicycleType getBicycleType() {
        return bicycleType;
    }

}
