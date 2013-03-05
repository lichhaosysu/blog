package com.lichhao.blog.common;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.springframework.transaction.annotation.Transactional;

public abstract class BaseDao<T> {

	@PersistenceContext
	protected EntityManager entityManager;

	@PersistenceUnit
	protected EntityManagerFactory entityManagerFactory;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private Class<T> entityClass;

	/**
	 * 通过反射获取子类确定的泛型类
	 */
	public BaseDao() {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		entityClass = (Class) params[0];
	}

	/**
	 * 根据ID获取POJO实例
	 * 
	 * @param id
	 * @return 返回该id相应的持久化POJO实例
	 */
	public T find(Serializable id) {
		return entityManager.find(entityClass, id);
	}

	/**
	 * 获取全部该类型的POJO实例
	 * 
	 * @return 所有该类型的POJO实例
	 */
	public List<T> findAll() {
		return entityManager.createQuery(
				" from " + entityClass.getSimpleName(), entityClass)
				.getResultList();
	}

	/**
	 * 保存POJO实例
	 * 
	 * @param entity
	 */
	public void save(T entity) {
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			entityManager.persist(entity);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}

	}

	/**
	 * 更新POJO实例
	 * 
	 * @param entity
	 */
	public T update(T entity) {

		T t = null;
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			t = entityManager.merge(entity);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}

		return t;
	}

	/**
	 * 删除POJO实例
	 * 
	 * @param entity
	 */
	public void delete(T entity) {
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			entity = entityManager.merge(entity);
			entityManager.remove(entity);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		}

	}

}
