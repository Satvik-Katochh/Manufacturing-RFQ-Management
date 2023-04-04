package com.mfpe.plant.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.ToString;
@ToString
@Entity
@Table(name="reorderrules")
public class ReorderRules {
	@Id
	private int  partId;
	//private Integer demandId;
	private int  minQuantity;
	private int maxQuantity;
	//private Integer reorderFrequency;
	public int getPartId() {
		return partId;
	}
	public void setPartId(int partId) {
		this.partId = partId;
	}
	public int getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}
	public int getMaxQuantity() {
		return maxQuantity;
	}
	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	public ReorderRules(int partId, int minQuantity, int maxQuantity) {
		super();
		this.partId = partId;
		this.minQuantity = minQuantity;
		this.maxQuantity = maxQuantity;
	}
	public ReorderRules() {
		super();
	}
}
