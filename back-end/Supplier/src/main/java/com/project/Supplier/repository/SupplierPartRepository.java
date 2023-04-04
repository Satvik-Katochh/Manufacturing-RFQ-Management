package com.project.Supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.Supplier.entity.Supplier;
import com.project.Supplier.entity.SupplierPart;
@Repository
public interface SupplierPartRepository extends JpaRepository<SupplierPart, Integer> {
	@Query(value="select s from SupplierPart s where s.partId=?1")
	public List <SupplierPart> getByPartId(int id);
}