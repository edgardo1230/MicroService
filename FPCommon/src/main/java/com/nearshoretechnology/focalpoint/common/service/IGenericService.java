package com.nearshoretechnology.focalpoint.common.service;

import java.io.Serializable;

import rx.Observable;
import rx.Single;

public interface IGenericService<T, ID extends Serializable>{

	Single<T> getById(ID id);

	Single<T> add(T obj);

	Single<T> edit(T obj);

	Single<Boolean> delete(T object);

	Observable<T> getAll(String... order);
    
}
