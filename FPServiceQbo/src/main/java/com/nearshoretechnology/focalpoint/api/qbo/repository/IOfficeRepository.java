package com.nearshoretechnology.focalpoint.api.qbo.repository;

import com.nearshoretechnology.focalpoint.api.qbo.model.Office;
import com.nearshoretechnology.focalpoint.common.dao.IGenericDao;

public interface IOfficeRepository extends IGenericDao<Office, Long> {

	// @Query(value = "SELECT o FROM Office o ORDER BY o.name ASC")
	// List<Office> findAllOrderByName();
}
