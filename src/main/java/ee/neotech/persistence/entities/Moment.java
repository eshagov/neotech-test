package ee.neotech.persistence.entities;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "moment")
public class Moment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "time_value")
    private Timestamp timeValue;

    public Moment() {
    }

    public Moment(Timestamp timeValue) {
        this.timeValue = timeValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimeValue() {
        return timeValue;
    }

    public void setTimeValue(Timestamp timeValue) {
        this.timeValue = timeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Moment moment = (Moment) o;
        return timeValue.equals(moment.timeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeValue);
    }

    @Override
    public String toString() {
        return "Moment{" +
                "id=" + id +
                ", timeValue=" + timeValue +
                '}';
    }

}