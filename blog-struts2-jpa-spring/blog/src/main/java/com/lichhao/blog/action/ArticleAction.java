package com.lichhao.blog.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.lichhao.blog.dao.ArticleDao;
import com.lichhao.blog.model.Article;

@ParentPackage("basePackage")
@Namespace("/")
public class ArticleAction extends BaseAction {

	@Autowired
	private ArticleDao articleDao;

	private Article article;

	private List<Article> articles;

	@Action(value = "admin", results = { @Result(name = "admin", type = "freemarker", location = "/WEB-INF/ftl/admin.ftl") })
	public String admin() throws Exception {
		
		String contextPath = request.getContextPath();
		request.setAttribute("contextPath", contextPath);
		
		return "admin";
	}

	@Action(value = "index2", results = { @Result(name = "viewArticle", type = "freemarker", location = "/WEB-INF/ftl/viewArticle.ftl") })
	public String index2() throws Exception {
		return "viewArticle";
	}

	@Action(value = "saveArticle", results = { @Result(type = "redirectAction", name = "saveArticle", params = {
			"actionName", "viewArticle?article.articleId=${article.articleId}" }) })
	public String saveArticle() throws Exception {
		if (!StringUtils.isEmpty(article.getArticleId())) {
			Date date = new Date();
			article.setModifyDate(date);
			articleDao.update(article);
		} else {
			Date date = new Date();
			article.setCreateDate(date);
			article.setModifyDate(date);
			article.setVisitCount(0);
			articleDao.save(article);
		}
		return "saveArticle";
	}

	@Action(value = "listArticles", results = { @Result(name = "listArticles", location = "/jsp/article/listArticles.jsp") })
	public String listArticles() throws Exception {
		articles = articleDao.findAllArticles();
		return "listArticles";
	}

	@Action(value = "viewArticle", results = { @Result(name = "viewArticle", location = "/jsp/article/success.jsp") })
	public String viewArticle() throws Exception {
		article = articleDao.findArticleById(article.getArticleId());
		return "viewArticle";
	}

	@Action(value = "editArticle", results = { @Result(name = "editArticle", location = "/jsp/index.jsp") })
	public String editArticle() throws Exception {
		article = articleDao.findArticleById(article.getArticleId());
		return "editArticle";
	}

	@Action(value = "deleteArticle", results = { @Result(type = "redirectAction", name = "deleteArticle", params = {
			"actionName", "listArticles" }) })
	public String deleteArticle() throws Exception {
		article = articleDao.findArticleById(article.getArticleId());
		articleDao.delete(article);
		return "deleteArticle";
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}
