package com.mfpe.plant.repo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfpe.plant.model.Demand;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer>{
	@Query(value="select D from Demand D where D.partId = ?1")
	public Demand getByPartId(int partId);
	

	
	
}
