package com.apifilmeseries.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountCredentials implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String password;
//	private Date created;
//	private Date expiration;
//	private String accessToken;
//	private String refreshToken;
	
}
