package com.mfpe.rfq.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfpe.rfq.exception.PartNotFoundException;
import com.mfpe.rfq.model.Part;
import com.mfpe.rfq.model.RfqDetail;
import com.mfpe.rfq.model.Supplier;
import com.mfpe.rfq.model.SupplierPart;
import com.mfpe.rfq.repo.RfqDetailRepo;
import com.mfpe.rfq.restclient.PartClient;
import com.mfpe.rfq.restclient.SupplierClient;

@Service
public class RfqService {

	@Autowired
	private RfqDetailRepo rfqDetailRepo;

	@Autowired
	private PartClient partClient;

	@Autowired
	private SupplierClient supplierClient;

	/*
	 * final private RfqRepo rfqRepo; final private RfqDetailRepo rfqDetailRepo;
	 * final private SupplierClient supplierClient; final private PartClient
	 * partClient;
	 */

	public RfqDetail getRfqOfPlant(int partId) throws PartNotFoundException {
		List<Part> partList = partClient.viewPartsReorder();
		Part part = null;
		for (Part p : partList) {
			if (p.getPartId() == partId) {
				part = p;
				break;
			}
		}
		int demand = partClient.viewDemand(partId);

		RfqDetail rfqDetail = new RfqDetail();
		rfqDetail.setExpectedSupplyDate(new Date());
		rfqDetail.setPartId(partId);
		rfqDetail.setPartName(part.getPartDescription());
		rfqDetail.setSpecification(part.getPartSpecification());
		rfqDetail.setQuantity(demand);
		rfqDetail = rfqDetailRepo.save(rfqDetail);
		return rfqDetail;

	}

	public List<Supplier> getPotentialVendorsOfRfq(int rfqId,String token) throws Exception {
		// TODO Auto-generated method stub
		RfqDetail rfq=rfqDetailRepo.findById(rfqId).orElse(null);
		if(rfq==null)
			throw new Exception("rfq id not found");
		List<Supplier> supplierList= supplierClient.getSupplier(rfq.getPartId(), token);
		
		return supplierList;
	}
	
	
/*
	public List<Supplier> getPotentialVendorsOfRfq(Long rfqId) {
		List<RfqDetail> rfqDetailList = rfqDetailRepo.getRfqDetailsByRfqId(rfqId);
		List<Supplier> supplierList = new ArrayList<>();
		Long supplierId;
		Supplier supplier;
		for (RfqDetail detail : rfqDetailList) {
			supplierId = detail.getSupplierId();
			supplier = supplierClient.getSupplier(supplierId);
			if (supplier.getFeedback() >= 7)
				supplierList.add(supplier);
		}
		return supplierList;
	}
	*/
}
