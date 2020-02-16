package ee.neotech.persistence.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import ee.neotech.persistence.entities.Moment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MomentDaoImplTest {
    private static MomentDao momentDao;

    @BeforeAll
    static void setUp() {
        momentDao = new MomentDaoImpl();
    }


    @Test
    void saveIT() {
        //With Junit5 is possible to use PowerMock but with vintage only
        Moment moment = new Moment(Timestamp.valueOf(LocalDateTime.now()));
        momentDao.save(moment);
    }

    @Test
    void getAllIT() {
        //With Junit5 is possible to use PowerMock but with vintage only
        Moment moment = new Moment(Timestamp.valueOf(LocalDateTime.now()));
        momentDao.getAll();
    }

}