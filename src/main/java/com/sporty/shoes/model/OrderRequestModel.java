package com.sporty.shoes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderRequestModel {
	private int quantity;
	private double totalAmount;
	private String date;
}
