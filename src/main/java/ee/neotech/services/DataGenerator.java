package ee.neotech.services;

import java.sql.Timestamp;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import ee.neotech.services.consumers.MomentTaskConsumer;
import ee.neotech.services.publishers.MomentTaskPublisher;

public class DataGenerator {

    private static BlockingQueue<Timestamp> blockingQueue = new LinkedBlockingDeque<>(36000);

    public static void process() {
        Executor executor = Executors.newFixedThreadPool(2);
        executor.execute(new MomentTaskPublisher(blockingQueue));
        executor.execute(new MomentTaskConsumer(blockingQueue));
    }

}