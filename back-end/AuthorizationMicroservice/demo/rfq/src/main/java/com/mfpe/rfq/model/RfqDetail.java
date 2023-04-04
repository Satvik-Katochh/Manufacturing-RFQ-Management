package com.mfpe.rfq.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "rfq_details")
public class RfqDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rfqId;
	private int partId;
	private String partName;
	private Date expectedSupplyDate;
	private String specification;
	private int quantity;

}
