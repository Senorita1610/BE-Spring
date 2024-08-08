package com.sportshop.service;

import java.util.List;

import com.sportshop.dto.BillDTO;
import com.sportshop.dto.PackageDTO;

public interface IBillService {
	List<BillDTO> getAll();

	BillDTO get(Long id);

	BillDTO create(BillDTO billDTO);

	BillDTO update(BillDTO billDTO);

	boolean delete(Long id);

	BillDTO createByCustomer(String phoneNumber, PackageDTO packageDTO);
}
