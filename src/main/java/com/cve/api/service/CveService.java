package com.cve.api.service;

import java.util.List;

import com.cve.api.entity.CveDetails;

public interface CveService {

	public Object getCveDataPagination(Integer page, Integer index);

	public Object getCveData();

	public Object addCveData();

	public CveDetails getById(String id);

	public List<CveDetails> getByScore(Double score);

}