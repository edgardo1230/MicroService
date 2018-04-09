package com.nearshoretechnology.focalpoint.api.admin.repository;

import com.nearshoretechnology.focalpoint.common.dao.IGenericDao;
import com.nearshoretechnology.focalpoint.api.admin.model.Office;

public interface IOfficeRepository extends IGenericDao<Office, Long> {

	// @Query(value = "SELECT o FROM Office o ORDER BY o.name ASC")
	// List<Office> findAllOrderByName();
}
