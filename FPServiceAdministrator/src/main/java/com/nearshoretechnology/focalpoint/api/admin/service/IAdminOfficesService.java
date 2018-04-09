package com.nearshoretechnology.focalpoint.api.admin.service;

import com.nearshoretechnology.focalpoint.api.admin.model.Office;
import com.nearshoretechnology.focalpoint.common.form.AdminOfficeDTO;
import com.nearshoretechnology.focalpoint.common.service.IGenericService;

import rx.Observable;

public interface IAdminOfficesService extends IGenericService<Office, Long> {

	Observable<Office> findAllOffices(AdminOfficeDTO form);

	void createNewOffice(AdminOfficeDTO form);

	void updateOffice(AdminOfficeDTO form);

	// void setIndexPresenter(AdminOfficesOutput presenter);
}
