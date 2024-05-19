package com.cve.api.response;

import com.cve.api.entity.CveDetails;

import lombok.Data;

@Data
public class Vulnerabilitiy {
	private CveDetails cve;
}
