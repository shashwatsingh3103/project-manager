package com.springboot.projectmanager.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class authresponse {

	private String jwt ;
	private String message ;
}
