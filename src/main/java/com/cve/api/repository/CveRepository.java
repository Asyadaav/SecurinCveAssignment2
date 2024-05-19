package com.cve.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cve.api.entity.CveDetails;

public interface CveRepository extends MongoRepository<CveDetails, String> {

}
