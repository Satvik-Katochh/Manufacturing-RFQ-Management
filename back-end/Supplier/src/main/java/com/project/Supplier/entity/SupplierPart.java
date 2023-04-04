package com.project.Supplier.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/*import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;*/
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class SupplierPart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int supplierPartId;

	// @NotNull(message = "Part ID is required")
	private int partId;

	// @NotBlank(message = "Part name is required")
	private String supplierPartName;

	// @NotNull(message = "Quantity is required")
	private int quantitiy;

	// @NotNull(message = "Time period is required")
	private Date deliveryDate;

	@ManyToOne
	@JoinColumn(name = "SupplierId")
	private Supplier supplier;

}
