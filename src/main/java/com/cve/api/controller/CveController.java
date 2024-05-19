package com.cve.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cve.api.entity.CveDetails;
import com.cve.api.service.CveService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/cve")
public class CveController {

	@Autowired
	private CveService cveService;

	// Endpoint to get CVE data with pagination
	@GetMapping(value = "/all/{page}/{index}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get CVE data with pagination", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved CVE data"),
			@ApiResponse(code = 404, message = "No CVE data found") })
	public ResponseEntity<Object> getCveDataPagination(@PathVariable Integer page, @PathVariable Integer index) {
		Object response = cveService.getCveDataPagination(page, index);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	// Endpoint to add CVE data to the database
	@GetMapping(value = "/add/db")
	@ApiOperation(value = "Test endpoint to add CVE data to the database", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully added CVE data"),
	@ApiResponse(code = 500, message = "Internal server error") })
//	@Scheduled(cron = "*/2 * * * * ?") // fpr every 5 second
	@Scheduled(cron = "0 0 0 * * *")// For every day
	public ResponseEntity<Object> addData() {
		Object response = cveService.addCveData();
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	// Endpoint to get CVE data by ID
	@GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get CVE data by ID", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved CVE by ID"),
			@ApiResponse(code = 404, message = "CVE with given ID not found") })
	public ResponseEntity<CveDetails> getCveById(@PathVariable String id) {
		CveDetails response = cveService.getById(id);
		return new ResponseEntity<CveDetails>(response, HttpStatus.OK);
	}

	// Endpoint to get CVE data by score
	@GetMapping(value = "/score/{score}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get CVE data by score", response = ResponseEntity.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved CVE by score"),
			@ApiResponse(code = 404, message = "No CVE found with given score") })
	public ResponseEntity<List<CveDetails>> getCveByScore(@PathVariable Double score) {
		List<CveDetails> response = cveService.getByScore(score);
		return new ResponseEntity<List<CveDetails>>(response, HttpStatus.OK);
	}
}