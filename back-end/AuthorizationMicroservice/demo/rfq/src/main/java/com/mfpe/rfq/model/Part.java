package com.mfpe.rfq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Part {
	private int partId;
	private String PartDescription;
	private String PartSpecification;
	private int stockInHand;

}
