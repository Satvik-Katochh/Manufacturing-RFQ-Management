package com.mfpe.rfq.restclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.mfpe.rfq.model.Supplier;
import com.mfpe.rfq.model.SupplierPart;

@FeignClient(name = "SupplierClient", url = "${supplier.url}")
public interface SupplierClient {
	/*
	 * @GetMapping("/getSupplierOfPart/{partId}") public List<SupplierPart>
	 * getSupplierOfPart(@PathVariable Long partId, @RequestHeader("Authorization")
	 * final String token) throws Exception;
	 */
	@GetMapping("/getsupplier/{id}")
    public List<Supplier> getSupplier(@PathVariable("id") int id) throws Exception;
}
