package com.mfpe.plant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.ToString;


@ToString
@Entity(name="part")
public class Part {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int partId;
	private String partDescription;
	private String partSpecification;
	private int stockInHand;
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
	}
	public String getPartDescription() {
		return partDescription;
	}
	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}
	public String getPartSpecification() {
		return partSpecification;
	}
	public void setPartSpecification(String partSpecification) {
		this.partSpecification = partSpecification;
	}
	public int getStockInHand() {
		return stockInHand;
	}
	public void setStockInHand(int stockInHand) {
		this.stockInHand = stockInHand;
	}
	public Part(int partId, String partDescription, String partSpecification, int stockInHand) {
		super();
		this.partId = partId;
		this.partDescription = partDescription;
		this.partSpecification = partSpecification;
		this.stockInHand = stockInHand;
	}
	public Part() {
		super();
	}

}
