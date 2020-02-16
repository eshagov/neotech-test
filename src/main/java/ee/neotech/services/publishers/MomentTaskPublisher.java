package ee.neotech.services.publishers;

import java.sql.Timestamp;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.Clock;
import java.time.LocalDateTime;

import org.jboss.logging.Logger;

public class MomentTaskPublisher implements Runnable {

    private Logger logger = Logger.getLogger(MomentTaskPublisher.class);

    private BlockingQueue<Timestamp> blockingQueue;

    public MomentTaskPublisher(BlockingQueue<Timestamp> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> sendForProcessing(),
                                            1, 1, TimeUnit.SECONDS);
    }

    private void sendForProcessing() {
        final Timestamp currentTimestamp = getCurrentTimestamp();

        if (blockingQueue.offer(currentTimestamp)) {
            logger.info("Timestamp " + currentTimestamp + " sent for processing.");
        } else {
            logger.info("Timestamp " + currentTimestamp + " couldn't be sent for processing");
        }
    }

    private Timestamp getCurrentTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()));
    }

}