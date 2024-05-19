package com.cve.api.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "cve_details")
public class CveDetails {

	@Id
	private String id;

	private String sourceIdentifier;
	private String published;
	private String lastModified;
	private String vulnStatus;
	private List<Description> descriptions;
	private Metrics metrics;
	private List<Weakness> weaknesses;
	private List<Configuration> configurations;
	private List<Reference> references;
}

@Data
class Weakness {
	private String source;
	private String type;
	private List<Description> description;
}

@Data
class Description {
	private String lang;
	private String value;
}

@Data
class Configuration {
	private List<Nodes> nodes;
}

@Data
class Nodes {
	private String operator;
	private Boolean negate;
	private List<CpeMatch> cpeMatch;
}

@Data
class CpeMatch {
	private Boolean vulnerable;
	private String criteria;
	private String matchCriteriaId;
}

@Data
class Reference {
	private String url;
	private String source;
}