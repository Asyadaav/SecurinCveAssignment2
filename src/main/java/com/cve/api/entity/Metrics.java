package com.cve.api.entity;

import java.util.List;

import lombok.Data;

@Data
public class Metrics {
	private List<CvssMetricV2> cvssMetricV2;
}
