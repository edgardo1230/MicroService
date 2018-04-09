package com.nearshoretechnology.focalpoint.common.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.nearshoretechnology.focalpoint.common.dao.IGenericDao;

@Repository
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@CacheConfig(cacheResolver = "primaryCacheResolver")
public class GenericJpaDao<T, ID extends Serializable> implements IGenericDao<T, ID> {
	private static final Logger LOG = LoggerFactory.getLogger(GenericJpaDao.class);

	private Class<T> clazz;
	
	@PersistenceContext
	EntityManager entityManager;

	public GenericJpaDao(Class<T> clazzToSet, ID idToSet) {
		this.clazz = clazzToSet;
	}

	@Cacheable(key = "#id", unless = "#result == null")
	public T findOne(ID id) {
		return entityManager.find(clazz, id);
	}

	@Cacheable(cacheResolver = "secondaryCacheResolver", unless = "#result == null")
	public List<T> findAll(String... order) {
		LOG.debug(" List<T> findAll("+Arrays.toString(order)+")");
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(clazz);
		Root<T> root = criteria.from(clazz);
		if (order.length > 0) {
			List<Order> orderList = new ArrayList<Order>();
			for (int i = 0; i < order.length; i++) {
				orderList.add(builder.asc(root.get(order[i])));
			}
			criteria.orderBy(orderList);
		}
		return entityManager.createQuery(criteria).getResultList();
	}

	@Caching(put = { @CachePut(key = "#object.id") }, evict = {
			@CacheEvict(cacheResolver = "secondaryCacheResolver", allEntries = true) })
	public void create(T entity) {
		entityManager.persist(entity);
	}

	@Caching(put = { @CachePut(key = "#object.id") }, evict = {
			@CacheEvict(cacheResolver = "secondaryCacheResolver", allEntries = true) })
	public T update(T entity) {
		return entityManager.merge(entity);
	}

	@Caching(evict = { @CacheEvict(key = "#object.id"),
			@CacheEvict(cacheResolver = "secondaryCacheResolver", allEntries = true) })
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@Caching(evict = { @CacheEvict(key = "#object.id"),
			@CacheEvict(cacheResolver = "secondaryCacheResolver", allEntries = true) })
	public void deleteById(ID entityId) {
		T entity = findOne(entityId);
		delete(entity);
	}

}