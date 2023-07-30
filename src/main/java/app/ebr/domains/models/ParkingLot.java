package app.ebr.domains.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "parkinglots")
public class ParkingLot {

    public ParkingLot() {

    }

    public ParkingLot(String location) {
        this.location = location;
    }

    public ParkingLot(String location, List<Bicycle> bicycles) {
        this.location = location;
        this.bicycles = bicycles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "location")
    private String location;

    @OneToMany(mappedBy = "parkingLot")
    private List<Bicycle> bicycles;

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setBicycles(List<Bicycle> bicycles) {
        this.bicycles = bicycles;
    }

    public List<Bicycle> getBicycles() {
        return bicycles;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

}
