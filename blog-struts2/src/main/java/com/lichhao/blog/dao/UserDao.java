package com.lichhao.blog.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lichhao.blog.common.BaseDao;
import com.lichhao.blog.model.User;

public class UserDao extends BaseDao<User> {

	/**
	 * 验证用户名和密码对应的用户在数据库是否存在
	 * 
	 * @param user
	 * @return Boolean
	 */
	public Boolean validateUser(User user) {
		TypedQuery<User> query = entityManager
				.createQuery(
						"from User where userName = :userName and userPassword = :userPassword order by createDate desc",
						User.class);
		query.setParameter("userName", user.getUserName());
		query.setParameter("userPassword", user.getUserPassword());

		List<User> userList = query.getResultList();

		return userList.size() != 0;
	}
}
