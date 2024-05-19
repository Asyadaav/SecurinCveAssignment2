package com.cve.api.entity;

import lombok.Data;

@Data
public class CvssData {
	private String version;
	private String vectorString;
	private String accessVector;
	private String accessComplexity;
	private String authentication;
	private String confidentialityImpact;
	private String integrityImpact;
	private String availabilityImpact;
	private Double baseScore;
}
