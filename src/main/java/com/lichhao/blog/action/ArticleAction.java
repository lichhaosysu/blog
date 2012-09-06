package com.lichhao.blog.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.lichhao.blog.dao.ArticleDao;
import com.lichhao.blog.model.Article;
import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class ArticleAction extends ActionSupport {

	@Autowired
	private ArticleDao articleDao;

	private Article article;

	private List<Article> articles;

	@Override
	public String execute() throws Exception {
		articleDao.save(article);
		return SUCCESS;
	}

	public String listArticles() throws Exception {

		articles = articleDao.findAllArticles();

		return "listArticles";
	}

	public String viewArticle() throws Exception {
		article = articleDao.findArticleById(article.getArticleId());
		return "viewArticle";
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
