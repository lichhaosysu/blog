package com.lichhao.blog.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lichhao.blog.common.BaseDao;
import com.lichhao.blog.model.Tag;

public class TagDao extends BaseDao<Tag> {

	/**
	 * 根据标签名查找标签
	 * 
	 * @param tagName
	 * @return 标签POJO实例
	 */
	public Tag findTagByTagName(String tagName) {
		TypedQuery<Tag> query = entityManager.createQuery(
				"from Tag where tagName = :tagName", Tag.class);
		query.setParameter("tagName", tagName);
		Tag tag = null;
		try {
			tag = query.getSingleResult();
		} catch (NoResultException e) {
		}
		return tag;
	}
}
