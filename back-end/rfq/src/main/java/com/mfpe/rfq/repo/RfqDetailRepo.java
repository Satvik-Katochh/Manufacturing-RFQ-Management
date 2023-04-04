package com.mfpe.rfq.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfpe.rfq.model.RfqDetail;

@Repository
public interface RfqDetailRepo extends JpaRepository<RfqDetail, Integer> {
	

}
