package com.sporty.shoes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "orderTable")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Order {

	@Id
	@GeneratedValue
	private int oid;

	@ManyToOne
	private User user;

	@ManyToOne
	private Product product;

	private int quantity;

	@Column(precision = 8, scale = 3)
	private double totalAmount;
	private String date;

}
