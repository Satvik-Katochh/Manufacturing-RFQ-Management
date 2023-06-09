package com.mfpe.plant.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="part")
public class Part {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int partId;
	private String partDescription;
	private String partSpecification;
	private int stockInHand;

}
