package com.utopian.springframework.bootstrap;

import com.utopian.springframework.domain.Ground;
import com.utopian.springframework.repositories.GroundRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Created by garamasu on 2017-01-03.
 * This class implements the ApplicationListner interface, so it gets called upon the ContextRefresedEvent on startup.
 * We’re using Spring to inject the Spring Data JPA repository into the class for our use.
 * In this example, I’m creating two entities and saving them to the database.
 */
@Component
public class GroundLoader implements ApplicationListener<ContextRefreshedEvent> {

    private GroundRepository groundRepository;

    private Logger log = Logger.getLogger(GroundLoader.class);

    @Autowired
    public void setGroundRepository(GroundRepository groundRepository) {
        this.groundRepository = groundRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Ground bilandeGround = new Ground();
        bilandeGround.setDescription("Spring Framework - 12 Stars Ground");
        bilandeGround.setRentalPrice(new BigDecimal("150.00"));
        bilandeGround.setImageUrl("http://cricket-belgium.com/wp-content/uploads/2014/12/12Stars-Ground-820x475-1.jpg");
        bilandeGround.setGroundId("1234");
        groundRepository.save(bilandeGround);

        log.info("Saved Bilande Ground - id: " + bilandeGround.getId());

        Ground aiccGround = new Ground();
        aiccGround.setDescription("Spring Framework - AICC Ground");
        aiccGround.setRentalPrice(new BigDecimal("100.00"));
        aiccGround.setImageUrl("http://cricket-belgium.com/wp-content/uploads/2014/12/AICC-Ground-633x475-3.jpg");
        aiccGround.setGroundId("4567");
        groundRepository.save(aiccGround);

        log.info("Saved AICC Ground - id:" + aiccGround.getId());
    }
}
