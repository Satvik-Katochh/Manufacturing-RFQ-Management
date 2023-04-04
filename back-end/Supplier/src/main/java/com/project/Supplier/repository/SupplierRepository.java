package com.project.Supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.Supplier.entity.Supplier;
import com.project.Supplier.entity.SupplierPart;
@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Integer> {
	
	@Query(value="select s from Supplier s where s.SupplierId=?1")
	public List <Supplier> getId(int id);
	

}
