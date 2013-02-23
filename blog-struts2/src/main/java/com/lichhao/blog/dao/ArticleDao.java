package com.lichhao.blog.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lichhao.blog.model.Article;
import com.lichhao.blog.model.Comment;
import com.lichhao.blog.model.Tag;
import com.lichhao.blog.model.User;
import com.lichhao.blog.util.Constants;




@Repository("articleDao")
public class ArticleDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Article article) {
		entityManager.persist(article);
	}

	@Transactional
	public Article update(Article article) {
		return entityManager.merge(article);
	}

	@Transactional
	public void delete(Article article) {
		article = entityManager.merge(article);
		entityManager.remove(article);
	}

	public List<Article> findAllArticles() {
		TypedQuery<Article> query = entityManager.createQuery(
				"from Article order by createDate desc", Article.class);
		List<Article> articles = query.getResultList();
		return articles;
	}

	public List<Article> findLatestArticles() {
		TypedQuery<Article> query = entityManager
				.createQuery(
						"from Article where isPublished = :isPublished order by createDate desc",
						Article.class);
		query.setParameter("isPublished", Boolean.TRUE);
		query.setMaxResults(5);
		return query.getResultList();
	}

	public List<Article> findArticlesByPage(int page) {

		int articlesPerPage = Constants.ARTICLES_PER_PAGE; // 每页显示15篇文章
		TypedQuery<Article> query = entityManager
				.createQuery(
						"from Article where isPublished = :isPublished order by createDate desc",
						Article.class);
		query.setParameter("isPublished", Boolean.TRUE);
		query.setFirstResult(articlesPerPage * (page - 1)).setMaxResults(
				articlesPerPage);
		List<Article> articles = query.getResultList();
		return articles;

	}

	public Integer getArticlesTotal() {
		Query query = entityManager
				.createNativeQuery("select count(1) from article where is_published = 1");
		return ((BigInteger) (query.getResultList().get(0))).intValue();
	}

	public Article findArticleById(Integer articleId) {
		TypedQuery<Article> query = entityManager.createQuery(
				"from Article where articleId = :articleId", Article.class);
		query.setParameter("articleId", articleId);
		Article article = null;
		try {
			article = query.getSingleResult();
		} catch (NoResultException e) {
		}
		return article;
	}

	public Article findPreArticle(Article article) {
		TypedQuery<Article> query = entityManager.createQuery(
				"from Article where createDate > :createDate", Article.class);
		query.setParameter("createDate", article.getCreateDate())
				.setMaxResults(1);
		Article result = null;
		List<Article> resultList = query.getResultList();
		if (resultList.size() == 0) {
			return result;
		} else {
			result = resultList.get(0);
			return result;
		}
	}

	public Article findNextArticle(Article article) {
		TypedQuery<Article> query = entityManager.createQuery(
				"from Article where createDate < :createDate", Article.class);
		query.setParameter("createDate", article.getCreateDate())
				.setMaxResults(1);
		Article result = null;
		List<Article> resultList = query.getResultList();
		if (resultList.size() == 0) {
			return result;
		} else {
			result = resultList.get(0);
			return result;
		}

	}

	public List<Tag> findAllTags() {
		TypedQuery<Tag> query = entityManager
				.createQuery("from Tag", Tag.class);
		List<Tag> tags = query.getResultList();
		return tags;
	}

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

	public Tag findTagByTagId(Integer tagId) {
		TypedQuery<Tag> query = entityManager.createQuery(
				"from Tag where tagId = :tagId", Tag.class);
		query.setParameter("tagId", tagId);
		Tag tag = null;
		try {
			tag = query.getSingleResult();
		} catch (NoResultException e) {
		}
		return tag;
	}

	@Transactional
	public void saveTag(Tag tag) {
		entityManager.persist(tag);
	}

	public Comment findCommentById(String commentId) {
		TypedQuery<Comment> query = entityManager.createQuery(
				"from Comment where commentId = :commentId", Comment.class);
		query.setParameter("commentId", commentId);
		Comment comment = null;
		try {
			comment = query.getSingleResult();
		} catch (NoResultException e) {
		}
		return comment;
	}

	public List<Comment> finLatestComments() {
		TypedQuery<Comment> query = entityManager.createQuery(
				"from Comment order by createDate desc", Comment.class);
		query.setMaxResults(5);
		return query.getResultList();
	}

	@Transactional
	public Comment updateComment(Comment comment) {
		return entityManager.merge(comment);
	}

	public Boolean validateUser(User user) {
		TypedQuery<User> query = entityManager
				.createQuery(
						"from User where userName = :userName and password = :password order by createDate desc",
						User.class);
		query.setParameter("userName", user.getUserName());
		query.setParameter("password", user.getUserPassword());

		List<User> userList = query.getResultList();

		return userList.size() != 0;
	}

}
