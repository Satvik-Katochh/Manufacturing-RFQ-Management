package com.mfpe.plant.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="PlantReorderDetails")
public class PlantReorderDetails {
	@Id
	private Integer partId;
	private String reorderStatus;
	private String reorderDate;
	

}
