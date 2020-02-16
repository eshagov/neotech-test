package ee.neotech.persistence.dao;

import java.util.List;

import ee.neotech.persistence.entities.Moment;

public interface MomentDao {
    void save(Moment moment);
    List<Moment> getAll();

}