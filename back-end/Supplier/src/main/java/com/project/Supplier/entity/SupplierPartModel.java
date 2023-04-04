package com.project.Supplier.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor


public class SupplierPartModel {
	
	private int supplierPartId;
	private int partId;
	private String supplierPartName;
	private int quantitiy;
	private Date deliveryDate;
	private int SupplierId;
	private String SupplierName;
	private String Location;
	private String email;
	private String phone;
	private int feedback;
	}
	
	

