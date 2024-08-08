package com.sportshop.service;

import java.util.List;

import com.sportshop.dto.PackageDTO;

public interface IPackageService {
	List<PackageDTO> getAll();

	PackageDTO get(Long id);

	PackageDTO create(PackageDTO packageDTO);

	PackageDTO update(PackageDTO packageDTO);

	boolean delete(Long id);
	
	PackageDTO getBySlug(String slug);

}
