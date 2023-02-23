package com.project.young.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.young.entity.CarLocation;
import com.project.young.producer.CarLocationProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CarLocationScheduler {

    private CarLocation carOne;
    private CarLocation carTwo;
    private CarLocation carThree;

    @Autowired
    private CarLocationProducer producer;

    public CarLocationScheduler() {
        long now = System.currentTimeMillis();
        carOne = new CarLocation("car-one", now, 0);
        carTwo = new CarLocation("car-two", now, 110);
        carThree = new CarLocation("car-three", now, 95);
    }

//    @Scheduled(fixedRate = 10000)
    public void generateLocation() throws JsonProcessingException {
        long now = System.currentTimeMillis();
        carOne.setTimestamp(now);
        carTwo.setTimestamp(now);
        carThree.setTimestamp(now);

        carOne.setDistance(carOne.getDistance() + 1);
        carTwo.setDistance(carTwo.getDistance() - 1);
        carThree.setDistance(carThree.getDistance() + 1);

        producer.send(carOne);
        producer.send(carTwo);
        producer.send(carThree);

        log.info("Sent: {}", carOne);
        log.info("Sent: {}", carTwo);
        log.info("Sent: {}", carThree);
    }
}
