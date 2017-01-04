package com.utopian.springframework.services;

import com.utopian.springframework.domain.Ground;

/**
 * Created by garamasu on 2017-01-03.
 */
public interface GroundService {
    Iterable<Ground> listAllGrounds();

    Ground getGroundById(Integer id);

    Ground saveGround(Ground ground);

    void deleteGround(Integer id);
}
