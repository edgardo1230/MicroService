package com.nearshoretechnology.focalpoint.api.admin.repository.impl;

import org.springframework.stereotype.Repository;

import com.nearshoretechnology.focalpoint.api.admin.model.Office;
import com.nearshoretechnology.focalpoint.api.admin.repository.IOfficeRepository;
import com.nearshoretechnology.focalpoint.common.dao.impl.GenericJpaDao;

@Repository
public class OfficeRespositoryImpl extends GenericJpaDao<Office, Long> implements IOfficeRepository  {

	public OfficeRespositoryImpl() {
		super(Office.class, 10L);
	}


}
