package com.utopian.springframework.services;

import com.utopian.springframework.domain.Ground;
import com.utopian.springframework.repositories.GroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by garamasu on 2017-01-03.
 */
@Service
public class GroundServiceImpl implements GroundService {
    private GroundRepository groundRepository;

    @Autowired
    public void setGroundRepository(GroundRepository productRepository) {
        this.groundRepository = productRepository;
    }

    @Override
    public Iterable<Ground> listAllGrounds() {
        return groundRepository.findAll();
    }

    @Override
    public Ground getGroundById(Integer id) {
        return groundRepository.findOne(id);
    }

    @Override
    public Ground saveGround(Ground product) {
        return groundRepository.save(product);
    }

    @Override
    public void deleteGround(Integer id) {
        groundRepository.delete(id);
    }
}