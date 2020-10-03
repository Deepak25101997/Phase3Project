package com.sporty.shoes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="tokenTable")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class MyToken {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String tokenValue;

}
