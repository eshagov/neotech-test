package ee.neotech.services;

import org.jboss.logging.Logger;

import ee.neotech.persistence.dao.MomentDao;
import ee.neotech.persistence.dao.MomentDaoImpl;

public class DataReporter {
    private static Logger logger = Logger.getLogger(DataReporter.class);

    public static void show() {
        MomentDao momentDao = new MomentDaoImpl();
        momentDao.getAll().forEach(moment -> logger.info(moment));
    }

}