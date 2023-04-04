package com.mfpe.rfq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
	private Long id;
	private String name;
	private String location;
	private String email;
	private String phone;
	private int feedback;

}
