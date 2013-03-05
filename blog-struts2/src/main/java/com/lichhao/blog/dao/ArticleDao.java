package com.lichhao.blog.dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.lichhao.blog.common.BaseDao;
import com.lichhao.blog.model.Article;
import com.lichhao.blog.util.Constants;

public class ArticleDao extends BaseDao<Article> {

	/**
	 * 查找最新的五篇已发表文章
	 * 
	 * @return 文章类型List列表
	 */
	public List<Article> findLatestArticles() {
		TypedQuery<Article> query = entityManager
				.createQuery(
						"from Article where isPublished = :isPublished and type='post' and article_status='public' order by createDate desc",
						Article.class);
		query.setParameter("isPublished", Boolean.TRUE);
		query.setMaxResults(5);
		return query.getResultList();
	}

	/**
	 * 分页查找文章
	 * 
	 * @param page
	 * @return 文章类型List列表
	 */
	public List<Article> findArticlesByPage(int page) {

		int articlesPerPage = Constants.ARTICLES_PER_PAGE; // 每页显示15篇文章
		TypedQuery<Article> query = entityManager
				.createQuery(
						"from Article where isPublished = :isPublished and type='post' and article_status='public' order by createDate desc",
						Article.class);
		query.setParameter("isPublished", Boolean.TRUE);
		query.setFirstResult(articlesPerPage * (page - 1)).setMaxResults(
				articlesPerPage);
		List<Article> articles = query.getResultList();
		return articles;

	}

	/**
	 * 获取已发表文章总数
	 * 
	 * @return 已发表文章总数
	 */
	public Integer getArticlesTotal() {
		Query query = entityManager
				.createNativeQuery("select count(1) from article where is_published = 1");
		return ((BigInteger) (query.getResultList().get(0))).intValue();
	}

	/**
	 * 查找前一篇文章
	 * 
	 * @param article
	 * @return 文章POJO实例
	 */
	public Article findPreArticle(Article article) {
		TypedQuery<Article> query = entityManager
				.createQuery(
						"from Article where createDate > :createDate and type='post' and article_status='public'",
						Article.class);
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

	/**
	 * 查找下一篇文章
	 * 
	 * @param article
	 * @return 文章POJO实例
	 */
	public Article findNextArticle(Article article) {
		TypedQuery<Article> query = entityManager
				.createQuery(
						"from Article where createDate < :createDate and type='post' and article_status='public'",
						Article.class);
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

}
