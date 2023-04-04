package com.project.Supplier.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.project.Supplier.entity.Supplier;
import com.project.Supplier.entity.SupplierPart;
import com.project.Supplier.entity.SupplierPartModel;
import com.project.Supplier.exception.SupplierPartEmptyException;
import com.project.Supplier.exception.SupplierPartNotFoundException;
import com.project.Supplier.repository.SupplierPartRepository;
import com.project.Supplier.repository.SupplierRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SupplierService {

	@Autowired
	private SupplierPartRepository supplierPartRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	public List<Supplier> findByPartId(int id) throws SupplierPartNotFoundException {
		log.info("Inside FindSupplierByPartId of SupplierServive");

		List<SupplierPart> list = supplierPartRepository.getByPartId(id);
		List<Supplier> list1 = new ArrayList<>();
		for (SupplierPart s : list)
			list1.add(s.getSupplier());
		log.info("[Supplier details:]");
		return list1;

	};

	public SupplierPart addSupplier(SupplierPartModel supplierPartModel) throws Exception {
		log.info("Inside FindSupplierByPartId of Supplier Service");
		Supplier supplier = new Supplier();
		supplier.setSupplierName(supplierPartModel.getSupplierName());
		supplier.setLocation(supplierPartModel.getLocation());
		supplier.setEmail(supplierPartModel.getEmail());
		supplier.setPhone(supplierPartModel.getPhone());

		supplier.setFeedback(supplierPartModel.getFeedback());
		supplier = supplierRepository.save(supplier);

		SupplierPart supplierPart = new SupplierPart();
		supplierPart.setSupplier(supplier);
		supplierPart.setSupplierPartName(supplierPartModel.getSupplierPartName());
		supplierPart.setQuantitiy(supplierPartModel.getQuantitiy());
		supplierPart.setDeliveryDate(supplierPartModel.getDeliveryDate());
		supplierPart.setPartId(supplierPartModel.getPartId());

		supplierPart = supplierPartRepository.save(supplierPart);
		log.info("[Supplier Part details:]");
		return supplierPart;

	}

	public Supplier editSupplier(SupplierPartModel supplierPartModel) throws Exception {

		Supplier editSupplier = supplierRepository.findById(supplierPartModel.getSupplierId()).orElse(null);
		if (editSupplier == null) {
			log.error("Supplier not found");

			throw new Exception("Supplier not found");

		} else {
			editSupplier.setSupplierId(supplierPartModel.getSupplierId());
			editSupplier.setSupplierName(supplierPartModel.getSupplierName());
			editSupplier.setLocation(supplierPartModel.getLocation());
			editSupplier.setEmail(supplierPartModel.getEmail());
			editSupplier.setPhone(supplierPartModel.getPhone());
			editSupplier.setFeedback(supplierPartModel.getFeedback());

			return supplierRepository.save(editSupplier);

		}
	}

	public void updateFeedback(SupplierPartModel supplierPartModel) throws Exception {
		log.info("Inside updateFeedback of Supplier Service");
		Supplier editSupplier = supplierRepository.findById(supplierPartModel.getSupplierId()).orElse(null);
		if (editSupplier == null) {
			log.error("Supplier not found");

			throw new Exception("Supplier not found");
		} else {

			editSupplier.setFeedback(supplierPartModel.getFeedback());

			supplierRepository.save(editSupplier);
		}

	}

	public List<Supplier> getSupplier(int partId) {
		// TODO Auto-generated method stub
		log.info("getSupplier of Supplier Service");
		List<Supplier> supplierList = new ArrayList<>();
		// logic
		List<SupplierPart> list = supplierPartRepository.getByPartId(partId);

		for (SupplierPart sp : list) {
			if (sp.getSupplier().getFeedback() > 7)
				supplierList.add(sp.getSupplier());
		}
		log.info("[Supplier details:]");
		return supplierList;
	}

	public Supplier toSupplier(Supplier supplier) {
		Supplier supplier1 = new Supplier();
		supplier1.setSupplierId(supplier.getSupplierId());
		supplier1.setSupplierName(supplier.getSupplierName());
		supplier1.setEmail(supplier.getEmail());
		supplier1.setPhone(supplier.getPhone());
		supplier1.setLocation(supplier.getLocation());
		supplier1.setFeedback(supplier.getFeedback());
		return supplier1;
	}

}
