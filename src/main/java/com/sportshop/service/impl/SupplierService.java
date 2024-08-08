package com.sportshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportshop.converter.SupplierConverter;
import com.sportshop.dto.SupplierDTO;
import com.sportshop.entity.SupplierEntity;
import com.sportshop.exception.ValidateCommonException;
import com.sportshop.repository.SupplierRepository;
import com.sportshop.service.ISupplierService;
import com.sportshop.utils.InputValidateUtil;
import com.sportshop.utils.MsgUtil;

@Service
public class SupplierService implements ISupplierService {
	@Autowired
	SupplierRepository supplierRepo;
	@Autowired
	SupplierConverter supplierConverter;

	@Override
	public List<SupplierDTO> getAll() {
		List<SupplierEntity> list = supplierRepo.findAll();
		List<SupplierDTO> result = new ArrayList<>();
		for (SupplierEntity en : list) {
			SupplierDTO dto = supplierConverter.toDTO(en);
			result.add(dto);
		}
		return result;
	}

	@Override
	public SupplierDTO get(Long id) {
		SupplierEntity en = supplierRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("supplier.not.exists", "id", id));
		});
		SupplierDTO dto = supplierConverter.toDTO(en);
		return dto;
	}

	@Override
	public SupplierDTO create(SupplierDTO request) {
		String address = request.getAddress();
		String description = request.getDescription();
		String name = request.getName();
		String product = request.getProduct();
		Integer quantity = request.getQuantity();
		InputValidateUtil.validateNotNull("address", address);
		InputValidateUtil.validateNotNull("description", description);
		InputValidateUtil.validateNotNull("name", name);
		InputValidateUtil.validateNotNull("product", product);
		supplierRepo.findByName(name).ifPresent(u -> {
			throw new ValidateCommonException(MsgUtil.getMessage("supplier.exists", "name", name));
		});

		SupplierEntity supplier = supplierConverter.toEntity(request);
		SupplierDTO dto = supplierConverter.toDTO(supplierRepo.save(supplier));
		return dto;
	}

	@Override
	public SupplierDTO update(SupplierDTO request) {
		Long supplierId = request.getSupplierId();
		String address = request.getAddress();
		String description = request.getDescription();
		String name = request.getName();
		String product = request.getProduct();
		Integer quantity = request.getQuantity();
		InputValidateUtil.validateNotNull("address", address);
		InputValidateUtil.validateNotNull("description", description);
		InputValidateUtil.validateNotNull("name", name);
		InputValidateUtil.validateNotNull("product", product);
		SupplierEntity supplier = supplierRepo.findById(supplierId).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("supplier.not.exists", "id", supplierId));
		});
		if(!supplier.getName().equals(name)) {
			supplierRepo.findByName(name).ifPresent(u -> {
				throw new ValidateCommonException(MsgUtil.getMessage("supplier.exists", "name", name));
			});
		}
		SupplierEntity en = supplierConverter.toEntity(request);
		supplier.setAddress(en.getAddress());
		supplier.setDescription(en.getDescription());
		supplier.setName(en.getName());
		supplier.setProduct(en.getProduct());
		supplier.setQuantity(en.getQuantity());
		SupplierDTO dto = supplierConverter.toDTO(supplierRepo.save(supplier));
		return dto;
	}

	@Override
	public boolean delete(Long id) {
		if (id <= 0) {
			throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "id"));
		}
		supplierRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("supplier.not.exists", "id", id));
		});
		supplierRepo.deleteById(id);
		return Boolean.TRUE;
	}

}
