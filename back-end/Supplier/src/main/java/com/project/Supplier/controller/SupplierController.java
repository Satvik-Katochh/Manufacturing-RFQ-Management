package com.project.Supplier.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Supplier.entity.Supplier;
import com.project.Supplier.entity.SupplierPart;
import com.project.Supplier.entity.SupplierPartModel;
import com.project.Supplier.exception.SupplierPartNotFoundException;
import com.project.Supplier.service.SupplierService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin("*")
@Slf4j
@RestController
public class SupplierController {

	@Autowired
	private SupplierService supplierService;

	@GetMapping(value = "/viewsupplierofart/{id}")
	public ResponseEntity<List<Supplier>> getSupplierPartById(@PathVariable("id") int id) throws SupplierPartNotFoundException {
		log.info("Inside viewSupplierOfpart of SupplierController");
		List<Supplier> list = supplierService.findByPartId(id);
		return new ResponseEntity<List<Supplier>>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/addSupplier")
	public ResponseEntity<SupplierPart> addSupplierPart(@RequestBody SupplierPartModel supplierPartModel)throws Exception {
		log.info("Inside addSupplier of SupplierController");
		SupplierPart supplierpart = supplierService.addSupplier(supplierPartModel);
		return new ResponseEntity<SupplierPart>(supplierpart, HttpStatus.OK);

	}

	@PostMapping("/editSupplier")
	public ResponseEntity<Supplier> editSupplier(@RequestBody SupplierPartModel supplierPartModel) throws Exception {
		log.info("Inside editSupplier of SupplierController");
		Supplier supplier = supplierService.editSupplier(supplierPartModel);
		return new ResponseEntity<Supplier>(supplier, HttpStatus.OK);

	}

	@PostMapping("/updateFeedback")
	public ResponseEntity<String> updateFeedback(@RequestBody SupplierPartModel supplierPartModel) throws Exception {
		log.info("Inside updateFeedback of SupplierController");
		supplierService.updateFeedback(supplierPartModel);

		return new ResponseEntity<String>("Updation done", HttpStatus.OK);

	}

	@GetMapping("/getsupplier/{partId}")
	public ResponseEntity<List<Supplier>> getSupplier(@PathVariable int partId) throws Exception {
		log.info("Inside getSupplier of SupplierController");
		List<Supplier> supplierList = supplierService.getSupplier(partId);
		return new ResponseEntity<>(supplierList, HttpStatus.OK);
	}

}
