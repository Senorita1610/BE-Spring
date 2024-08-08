package com.sportshop.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportshop.converter.PackageConverter;
import com.sportshop.dto.PackageDTO;
import com.sportshop.entity.PackageEntity;
import com.sportshop.exception.ValidateCommonException;
import com.sportshop.repository.PackageRepository;
import com.sportshop.service.IPackageService;
import com.sportshop.utils.InputValidateUtil;
import com.sportshop.utils.MsgUtil;
import com.sportshop.utils.Slugify;

@Service
public class PackageService implements IPackageService {
	@Autowired
	PackageRepository packageRepo;
	@Autowired
	PackageConverter packageConverter;

	@Override
	public List<PackageDTO> getAll() {
		List<PackageEntity> list = packageRepo.findAll();
		List<PackageDTO> result = new ArrayList<>();
		for (PackageEntity en : list) {
			PackageDTO dto = packageConverter.toDTO(en);
			result.add(dto);
		}
		return result;
	}

	@Override
	public PackageDTO get(Long id) {
		PackageEntity en = packageRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("package.not.exists", "id", id));
		});
		PackageDTO dto = packageConverter.toDTO(en);
		return dto;
	}

	@Override
	public PackageDTO create(PackageDTO request) {
		String description = request.getDescription();
		String subDesc = request.getSubDesc();
		String name = request.getName();
		Float price = request.getPrice();
		Integer quantity = request.getQuantity();
		LocalDateTime releaseDate = request.getReleaseDate();
		Integer validity = request.getValidity();
		InputValidateUtil.validateNotNull("description", description);
		InputValidateUtil.validateNotNull("subDesc", subDesc);
		InputValidateUtil.validateNotNull("name", name);

		packageRepo.findByName(name).ifPresent(u -> {
			throw new ValidateCommonException(MsgUtil.getMessage("package.exists", "name", name));
		});
		PackageEntity packageDetail = packageConverter.toEntity(request);
		packageDetail.setSlug(Slugify.toSlug(packageDetail.getName()));
		PackageDTO dto = packageConverter.toDTO(packageRepo.save(packageDetail));
		return dto;
	}

	@Override
	public PackageDTO update(PackageDTO request) {
		Long packageId = request.getPackageId();
		String description = request.getDescription();
		String name = request.getName();
		Float price = request.getPrice();
		Integer quantity = request.getQuantity();
		LocalDateTime releaseDate = request.getReleaseDate();
		Integer validity = request.getValidity();
		InputValidateUtil.validateNotNull("description", description);
		InputValidateUtil.validateNotNull("name", name);
		PackageEntity packageDetail = packageRepo.findById(packageId).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("package.not.exists", "id", packageId));
		});
		if(!packageDetail.getName().equals(name)) {
			packageRepo.findByName(name).ifPresent(u -> {
				throw new ValidateCommonException(MsgUtil.getMessage("package.exists", "name", name));
			});
		}
		PackageEntity en = packageConverter.toEntity(request);
		packageDetail.setSubDesc(en.getSubDesc());
		packageDetail.setDescription(en.getDescription());
		packageDetail.setName(en.getName());
		packageDetail.setSlug(Slugify.toSlug(en.getName()));
		packageDetail.setPrice(en.getPrice());
		packageDetail.setQuantity(en.getQuantity());
		packageDetail.setReleaseDate(en.getReleaseDate());
		packageDetail.setValidity(en.getValidity());
		packageDetail.setCategory(en.getCategory());
		packageDetail.setSupplier(en.getSupplier());
		PackageDTO dto = packageConverter.toDTO(packageRepo.save(packageDetail));
		return dto;
	}

	@Override
	public boolean delete(Long id) {
		if (id <= 0) {
			throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "id"));
		}
		packageRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("package.not.exists", "id", id));
		});
		packageRepo.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public PackageDTO getBySlug(String slug) {
		PackageEntity packageData = packageRepo.findBySlug(slug).orElseThrow();
		PackageDTO dto = packageConverter.toDTO(packageData);
		return dto;
	}

}
