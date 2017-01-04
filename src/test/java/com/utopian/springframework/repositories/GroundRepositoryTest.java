package com.utopian.springframework.repositories;

import com.utopian.springframework.configuration.RepositoryConfiguration;
import com.utopian.springframework.domain.Ground;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by garamasu on 2017-01-03.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class GroundRepositoryTest {

    private GroundRepository groundRepository;

    @Autowired
    public void setGroundRepository(GroundRepository groundRepository) {
        this.groundRepository = groundRepository;
    }

    @Test
    public void testSaveGround() {
        //setup ground
        Ground ground = new Ground();
        ground.setDescription("Spring Framework TestMe");
        ground.setRentalPrice(new BigDecimal("18.95"));
        ground.setGroundId("1234");

        //save ground, verify has ID value after save
        assertNull(ground.getId()); //null before save
        groundRepository.save(ground);
        assertNotNull(ground.getId()); //not null after save
        //fetch from DB
        Ground fetchedGround = groundRepository.findOne(ground.getId());

        //should not be null
        assertNotNull(fetchedGround);

        //should equal
        assertEquals(ground.getId(), fetchedGround.getId());
        assertEquals(ground.getDescription(), fetchedGround.getDescription());

        //update description and save
        fetchedGround.setDescription("New Description");
        groundRepository.save(fetchedGround);

        //get from DB, should be updated
        Ground fetchedUpdatedGround = groundRepository.findOne(fetchedGround.getId());
        assertEquals(fetchedGround.getDescription(), fetchedUpdatedGround.getDescription());

        //verify count of grounds in DB
        long groundCount = groundRepository.count();
        assertEquals(groundCount, 1);

        //get all grounds, list should only have one
        Iterable<Ground> grounds = groundRepository.findAll();

        int count = 0;

        for (Ground p : grounds) {
            count++;
        }

        assertEquals(count, 1);
    }
}