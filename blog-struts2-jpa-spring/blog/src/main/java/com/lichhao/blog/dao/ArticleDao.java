package com.lichhao.blog.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lichhao.blog.model.Article;
import com.lichhao.blog.model.Tag;

@Repository("articleDao")
public class ArticleDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Article article) {
		entityManager.persist(article);
	}

	@Transactional
	public void update(Article article) {
		entityManager.merge(article);
	}

	@Transactional
	public void delete(Article article) {
		article = entityManager.merge(article);
		entityManager.remove(article);
	}

	public List<Article> findAllArticles() {
		TypedQuery<Article> query = entityManager.createQuery("from Article",
				Article.class);
		List<Article> articles = query.getResultList();
		return articles;
	}

	public Article findArticleById(String articleId) {
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

	@Transactional
	public void saveTag(Tag tag) {
		entityManager.persist(tag);
	}

}
