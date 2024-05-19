package com.cve.api.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cve.api.entity.CveDetails;
import com.cve.api.entity.CvssMetricV2;
import com.cve.api.repository.CveRepository;
import com.cve.api.response.Response;
import com.cve.api.service.CveService;

@Service
public class CveServiceImpl implements CveService {

	@Autowired
	private CveRepository cveRepository;

	public Response getCveDataPagination(Integer page, Integer index) {
		RestTemplate restTemplate = new RestTemplate();
		Map<String, Object> params = new HashMap<>();
		params.put("resultsPerPage", page);
		params.put("startIndex", index);

		Response response = restTemplate.getForEntity(
				"https://services.nvd.nist.gov/rest/json/cves/2.0/?resultsPerPage={resultsPerPage}&startIndex={startIndex}",
				Response.class, params).getBody();
		return response;
	}

	public Response getCveData() {
		RestTemplate restTemplate = new RestTemplate();
		Response response = restTemplate
				.getForEntity("https://services.nvd.nist.gov/rest/json/cves/2.0", Response.class).getBody();
		return response;
	}

	@Override
	public Object addCveData() {
		Response response = getCveData();
		List<CveDetails> cveList = new ArrayList<>();
		if (response != null && response.getVulnerabilities().size() > 0) {
			response.getVulnerabilities().stream().forEach(data -> {
				cveList.add(data.getCve());
			});
//			cveList.stream().distinct().collect(Collectors.toList());
			cveList.stream().collect(Collectors.toConcurrentMap(CveDetails::getId, Function.identity(), (p, q) -> p)).values();
			cveRepository.saveAll(cveList);
			System.out.println("Data Inserted Successfully  :  "+LocalDateTime.now());
		}
		return "Data Inserted Successfully";
	}

	@Override
	public CveDetails getById(String id) {
		Optional<CveDetails> cvedata = cveRepository.findById(id);
		if (cvedata.isPresent()) {
			return cvedata.get();
		}
		return null;
	}

	@Override
	public List<CveDetails> getByScore(Double score) {
		List<CveDetails> filteredData = new ArrayList<>();
		List<CveDetails> list = cveRepository.findAll();
		for (CveDetails cve : list) {
			for (CvssMetricV2 cvssV2 : cve.getMetrics().getCvssMetricV2()) {
				if (cvssV2.getCvssData().getBaseScore().compareTo(score) == 0) {
					filteredData.add(cve);
				}
			}

		}
		return filteredData;
	}
}