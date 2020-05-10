package com.swb;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="users")
@JsonIgnoreProperties(ignoreUnknown=true)

public class User {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private long id;
	
	@Column
	private String name;
	
	
}
