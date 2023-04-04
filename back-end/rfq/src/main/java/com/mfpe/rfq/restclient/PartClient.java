package com.mfpe.rfq.restclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mfpe.rfq.exception.PartNotFoundException;
import com.mfpe.rfq.model.Part;

@FeignClient(name = "PartClient", url = "${part.url}")
public interface PartClient {
	@GetMapping("/viewpartsreorder")
	public List<Part> viewPartsReorder();
	
	@GetMapping("/viewdemand/{partId}")
	public Integer viewDemand(@PathVariable("partId") int partId) throws PartNotFoundException ;

}
