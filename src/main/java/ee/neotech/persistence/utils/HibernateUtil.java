package ee.neotech.persistence.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.jboss.logging.Logger;

public class HibernateUtil {
    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory;

    static {
        boolean isNotInitialized = true;
        while(isNotInitialized) {
            try {
                StandardServiceRegistry standardRegistry =
                        new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
                Metadata metaData =
                        new MetadataSources(standardRegistry).getMetadataBuilder().build();
                sessionFactory = metaData.getSessionFactoryBuilder().build();
                isNotInitialized = false;
            } catch (Throwable th) {
                logger.error("Initial SessionFactory creation failed: " + th.getMessage());
                waitForFiveSeconds();
            }
        }

    }

    private static void waitForFiveSeconds() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error("Internal issue: " + e.getMessage());
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void closeSessionFactory() {
        sessionFactory.close();
    }

}