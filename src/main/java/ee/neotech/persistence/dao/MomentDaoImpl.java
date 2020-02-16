package ee.neotech.persistence.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ee.neotech.exceptions.NeotechCustomException;
import ee.neotech.persistence.entities.Moment;
import ee.neotech.persistence.utils.HibernateUtil;

public class MomentDaoImpl implements MomentDao {

    @Override
    public void save(Moment moment) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(moment);
            transaction.commit();
        } catch (Throwable e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new NeotechCustomException("Problem with connection to DB");
        }
    }

    @Override
    public List<Moment> getAll() {
        List<Moment> moments;
        HibernateUtil.getSessionFactory();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            moments = session.createQuery("from Moment", Moment.class).list();
        } catch (Throwable ex) {
            throw new NeotechCustomException("Problem with connection to DB");
        }

        HibernateUtil.closeSessionFactory();

        return moments;
    }

}