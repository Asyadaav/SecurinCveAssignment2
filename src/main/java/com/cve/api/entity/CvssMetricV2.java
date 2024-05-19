package com.cve.api.entity;

import lombok.Data;

@Data
public class CvssMetricV2 {
	private String source;
	private String type;
	private CvssData cvssData;
	private String baseSeverity;
	private Double exploitabilityScore;
	private Double impactScore;
	private Boolean acInsufInfo;
	private Boolean obtainAllPrivilege;
	private Boolean obtainUserPrivilege;
	private Boolean obtainOtherPrivilege;
	private Boolean userInteractionRequired;
}
