package com.cve.api.response;

import java.util.List;

import lombok.Data;

@Data
public class Response {

	private Integer resultsPerPage;
	private Integer startIndex;
	private Integer totalResults;
	private String format;
	private String version;
	private String timestamp;
	private List<Vulnerabilitiy> vulnerabilities;
}
