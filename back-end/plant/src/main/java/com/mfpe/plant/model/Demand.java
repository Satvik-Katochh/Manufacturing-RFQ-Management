package com.mfpe.plant.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="Demand")
public class Demand {
	@Id
	private int  demandId;
	private int  partId;
	private int  demandCount;
	private String demandRaisedDate;
}
