package com.lichhao.blog.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lichhao.blog.model.Article;

@Repository("articleDao")
public class ArticleDao {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void save(Article article) {
		entityManager.persist(article);
	}

	public List<Article> findAllArticles() {
		TypedQuery<Article> query = entityManager.createQuery("from Article",
				Article.class);
		List<Article> articles = query.getResultList();
		return articles;
	}

	public Article findArticleById(Integer articleId) {
		TypedQuery<Article> query = entityManager.createQuery(
				"from Article where articleId = :articleId", Article.class);
		query.setParameter("articleId", articleId);

		Article article = query.getSingleResult();

		return article;

	}

}