package com.mfpe.plant.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.ToString;
@ToString
@Entity(name="Demand")
public class Demand {
	@Id
	private int  demandId;
	private int  partId;
	private int  demandCount;
	private String demandRaisedDate;
	
	
	
	public int getDemandId() {
		return demandId;
	}
	public void setDemandId(int demandId) {
		this.demandId = demandId;
	}
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
	}
	public int getDemandCount() {
		return demandCount;
	}
	public void setDemandCount(int demandCount) {
		this.demandCount = demandCount;
	}
	public String getDemandRaisedDate() {
		return demandRaisedDate;
	}
	public void setDemandRaisedDate(String demandRaisedDate) {
		this.demandRaisedDate = demandRaisedDate;
	}
	public Demand(int demandId, int partId, int demandCount, String demandRaisedDate) {
		super();
		this.demandId = demandId;
		this.partId = partId;
		this.demandCount = demandCount;
		this.demandRaisedDate = demandRaisedDate;
	}
	public Demand() {
		super();
	}
	
	
	
	
	

}
