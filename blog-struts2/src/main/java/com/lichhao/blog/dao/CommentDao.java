package com.lichhao.blog.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lichhao.blog.common.BaseDao;
import com.lichhao.blog.model.Comment;

public class CommentDao extends BaseDao<Comment> {

	/**
	 * 获取最新的5条评论
	 * 
	 * @return 评论类型的List列表
	 */
	public List<Comment> finLatestComments() {
		TypedQuery<Comment> query = entityManager.createQuery(
				"from Comment order by createDate desc", Comment.class);
		query.setMaxResults(5);
		return query.getResultList();
	}
}
