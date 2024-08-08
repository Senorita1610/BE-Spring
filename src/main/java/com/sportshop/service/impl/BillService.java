package com.sportshop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportshop.converter.BillConverter;
import com.sportshop.converter.PackageConverter;
import com.sportshop.dto.BillDTO;
import com.sportshop.dto.PackageDTO;
import com.sportshop.entity.BillEntity;
import com.sportshop.entity.CustomerEntity;
import com.sportshop.exception.ValidateCommonException;
import com.sportshop.repository.BillRepository;
import com.sportshop.repository.CustomerRepository;
import com.sportshop.repository.PaymentMethodRepository;
import com.sportshop.service.IBillService;
import com.sportshop.utils.MsgUtil;

@Service
public class BillService implements IBillService {
	@Autowired
	BillRepository billRepo;
	@Autowired
	CustomerRepository customerRepo;
	@Autowired
	PaymentMethodRepository paymentMethodrepo;
	@Autowired
	BillConverter billConverter;
	@Autowired
	PackageConverter packageConverter;

	@Override
	public List<BillDTO> getAll() {
		List<BillEntity> list = billRepo.findAll();
		List<BillDTO> result = new ArrayList<>();
		for (BillEntity en : list) {
			BillDTO dto = billConverter.toDTO(en);
			result.add(dto);
		}
		return result;
	}

	@Override
	public BillDTO get(Long id) {
		BillEntity en = billRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("bill.not.exists", "id", id));
		});
		BillDTO dto = billConverter.toDTO(en);
		return dto;
	}

	@Override
	public BillDTO create(BillDTO request) {
		BillEntity bill = billConverter.toEntity(request);
		BillDTO dto = billConverter.toDTO(billRepo.save(bill));
		return dto;
	}

	@Override
	public BillDTO update(BillDTO request) {
		Long billId = request.getBillId();
		BillEntity bill = billRepo.findById(billId).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("bill.not.exists", "id", billId));
		});
		BillEntity en = billConverter.toEntity(request);
		bill.setTotalPrice(en.getTotalPrice());
		bill.setCustomer(en.getCustomer());
		bill.setEmployee(en.getEmployee());
		bill.setPaymentMethod(en.getPaymentMethod());
		bill.setPromotion(en.getPromotion());
		bill.setPackageEntity(en.getPackageEntity());
		BillDTO dto = billConverter.toDTO(billRepo.save(bill));
		return dto;
	}

	@Override
	public boolean delete(Long id) {
		if (id <= 0) {
			throw new ValidateCommonException(MsgUtil.getMessage("input.invalid", "id"));
		}
		billRepo.findById(id).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("bill.not.exists", "id", id));
		});
		billRepo.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public BillDTO createByCustomer(String phoneNumber, PackageDTO packageDTO) {
		CustomerEntity customer = customerRepo.findByPhoneNumber(phoneNumber).orElseThrow(() -> {
			throw new ValidateCommonException(MsgUtil.getMessage("customer.not.exists", "phone", phoneNumber));
		});
		BillEntity en = new BillEntity();
		en.setTotalPrice(packageConverter.toEntity(packageDTO).getPrice());
		en.setCustomer(customer);
		en.setPackageEntity(packageConverter.toEntity(packageDTO));
		en.setPaymentMethod(paymentMethodrepo.getOne(2L));
		BillDTO dto = billConverter.toDTO(billRepo.save(en));
		return dto;
	}
}
