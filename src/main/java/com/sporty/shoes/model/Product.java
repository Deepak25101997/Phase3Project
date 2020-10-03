package com.sporty.shoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "productTable")
@Getter
@NoArgsConstructor
@Setter
@ToString
public class Product {

	@Id
	@GeneratedValue
	private int id;

	private String name;
	private String category;

	@Column(precision = 6, scale = 2)
	private double price;
}
