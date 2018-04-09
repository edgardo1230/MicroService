package com.nearshoretechnology.focalpoint.common.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDao<T, ID extends Serializable>  {

	T findOne(final ID id);

	List<T> findAll(String... order);

	void create(final T entity);

	T update(final T entity);

	void delete(final T entity);

	void deleteById(final ID entityId);

}
