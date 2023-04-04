package com.mfpe.rfq.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierPart {

	private int id;
    private int partId;
    private String partName;
    private int supplierId;
    private int quantity;
    private int timePeriod;
}
