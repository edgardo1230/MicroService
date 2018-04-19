package com.nearshoretechnology.focalpoint.api.qbo.service.impl;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nearshoretechnology.focalpoint.api.qbo.model.Office;
import com.nearshoretechnology.focalpoint.api.qbo.repository.IOfficeRepository;
import com.nearshoretechnology.focalpoint.api.qbo.service.IAdminOfficesService;
import com.nearshoretechnology.focalpoint.common.exception.PreconditionFailedException;
import com.nearshoretechnology.focalpoint.common.form.AdminOfficeDTO;
import com.nearshoretechnology.focalpoint.common.service.impl.GenericServiceImpl;
import com.nearshoretechnology.focalpoint.common.util.StringUtils;

import rx.Observable;

@Service
public class AdminOfficesServiceImpl extends GenericServiceImpl<Office, Long> implements IAdminOfficesService {

	@Autowired
	private IOfficeRepository iOfficeRepository;

	@PostConstruct
	private void init() {
		init(Office.class, 10L, iOfficeRepository);
	}

	private static final Logger LOG = LoggerFactory.getLogger(AdminOfficesServiceImpl.class);

	@Override
	public Observable<Office> findAllOffices(AdminOfficeDTO form) {
		LOG.info("findAllOffices");
		LOG.debug("Called with {}", form);

		return this.getAll("name");
	}

	@Override
	public void createNewOffice(AdminOfficeDTO form) {
		LOG.info("createNewOffice");
		LOG.debug("Called with {}", form);

		if (StringUtils.isBlank(form.getOffice())) {
			throw new PreconditionFailedException("Please provide a valid Office name!");
		}
		this.genericDao.create(new Office(form.getOffice()));
		// iOfficeRepository.save(new Office(form.getOffice()));
	}

	@Override
	public void updateOffice(AdminOfficeDTO form) {
		LOG.info("updateOffice");
		LOG.debug("Called with {}", form);

		if (StringUtils.isBlank(form.getOffice())) {
			throw new PreconditionFailedException("Please provide a valid Office name!");
		}

		// Office office = iOfficeRepository.findOne(form.getOfficeId());
		// Single<Office> office = this.getById(form.getOfficeId());
		// office.flatMapObservable(func)
		// if (office == null) {
		// throw new EntityNotFoundException("Office not found");
		// }
		//
		// office.setName(form.getOffice());
		// iOfficeRepository.save(office);
	}

	// @Override
	// public void setIndexPresenter(AdminOfficesOutput presenter) {
	//
	// indexPresenter = presenter;
	// }

}
