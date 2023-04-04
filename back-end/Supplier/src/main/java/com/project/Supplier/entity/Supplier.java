package com.project.Supplier.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;*/
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int SupplierId;
	

	//@NotBlank(message = "Supplier name is required")
	private String SupplierName;

	//@NotBlank(message = "Location is required")
	private String Location;

	//@Email(message = "Please enter valid email")
	//@NotBlank(message = "Email is required")
	private String email;

	//@NotBlank(message = "Phone No. is required")
	//@Pattern(regexp = "^[789][0-9]{9}", message = "Mobile number should be 10 digits and starting with 7/8/9")
	private String phone;

	//@Min(value = 1, message = "Value must be in range 1-10")
	//@Max(value = 10, message = "Value must be in range 1-10")
	//@NotNull(message = "Feedback is required")
	private int feedback;
}



