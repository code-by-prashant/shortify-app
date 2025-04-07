package com.shortify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShortenRequest {
	
	private String originalUrl;
	private String customCode;
	
}
