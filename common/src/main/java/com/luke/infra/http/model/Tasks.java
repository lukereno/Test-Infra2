package com.luke.infra.http.model;

import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

@Type("tasks")
public class Tasks {
	
	@Id
	private String id;

}
