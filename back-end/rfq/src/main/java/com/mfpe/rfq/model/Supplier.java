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
	private Long SupplierId;
	private String SupplierName;
	private String Location;
	private String email;
	private String phone;
	private int feedback;

}
