package com.nearshoretechnology.focalpoint.common.service.impl;

import java.io.Serializable;


import com.nearshoretechnology.focalpoint.common.dao.IGenericDao;
import com.nearshoretechnology.focalpoint.common.exception.DataAccessException;
import com.nearshoretechnology.focalpoint.common.service.IGenericService;

import rx.Observable;
import rx.Single;
import rx.exceptions.Exceptions;

public class GenericServiceImpl<T, ID extends Serializable> implements IGenericService<T, ID> {

	private Class<T> type;
	private ID id;
	protected IGenericDao<T, ID> genericDao;

	protected void init(Class<T> type, ID id, IGenericDao<T, ID> dao) {
		this.type = type;
		this.setId(id);
		this.genericDao = dao;
	}

	@Override
	public Single<T> getById(ID id) {
		try {
			return Single.just(genericDao.findOne(id)).map(o -> {
				if (o == null)
					throw Exceptions.propagate(new DataAccessException(DataAccessException.PROCESSING_FAILED, type.getSimpleName() + " : " + id));
				return o;
			});
		} catch (DataAccessException de) {
			return Single.error(de);
		}
	}

	@Override
	public Single<T> add(T obj) {
		try {
			genericDao.create(obj);
			return Single.just(obj);
		} catch (DataAccessException de) {
			return Single.error(de);
		}
	}

	@Override
	public Single<T> edit(T obj) {
		try {
			genericDao.update(obj);
			return Single.just(obj);
		} catch (DataAccessException de) {
			return Single.error(de);
		}
	}

	@Override
	public Single<Boolean> delete(T obj) {
		try {
			genericDao.delete(obj);
			return Single.just(true);
		} catch (DataAccessException de) {
			return Single.error(de);
		}
	}

	@Override
	public Observable<T> getAll(String... order) {
		try {
			return Observable.from(genericDao.findAll(order));
		} catch (DataAccessException de) {
			return Observable.error(de);
		}
	}

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

}
