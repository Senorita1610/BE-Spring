package com.sportshop.service;

import java.util.List;

import com.sportshop.dto.SupplierDTO;

public interface ISupplierService {

	List<SupplierDTO> getAll();

	SupplierDTO get(Long id);

	SupplierDTO create(SupplierDTO supplierDTO);

	SupplierDTO update(SupplierDTO supplierDTO);

	boolean delete(Long id);
}
