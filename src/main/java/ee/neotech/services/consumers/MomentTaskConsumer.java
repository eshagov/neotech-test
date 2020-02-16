package ee.neotech.services.consumers;

import java.sql.Timestamp;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.jboss.logging.Logger;

import ee.neotech.exceptions.NeotechCustomException;
import ee.neotech.persistence.dao.MomentDao;
import ee.neotech.persistence.dao.MomentDaoImpl;
import ee.neotech.persistence.entities.Moment;

public class MomentTaskConsumer implements Runnable {

    private Logger logger = Logger.getLogger(MomentTaskConsumer.class);
    private BlockingQueue<Timestamp> blockingQueue;

    public MomentTaskConsumer(BlockingQueue<Timestamp> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(() -> {
                    if (blockingQueue.size() > 0) {
                        while (!blockingQueue.isEmpty()) {
                            processToStorage();
                        }
                    }
                },
                1, 1, TimeUnit.SECONDS);
        blockingQueue.peek();
    }

    private void processToStorage() {
        final Timestamp moment = blockingQueue.peek();
        MomentDao momentDao = new MomentDaoImpl();
        try {
            momentDao.save(new Moment(moment));
            logger.info("Consumed: " + blockingQueue.take());
        } catch (NeotechCustomException | InterruptedException ex) {
            logger.error(ex.getMessage());
            waitForReconnect();
        }
    }

    private void waitForReconnect() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                logger.error("Internal issue: " + ex.getMessage());
            }
    }

}