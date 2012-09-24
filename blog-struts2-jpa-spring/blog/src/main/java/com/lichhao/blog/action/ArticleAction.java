package com.lichhao.blog.action;

import java.util.ArrayList;
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
import com.lichhao.blog.model.Tag;

@ParentPackage("basePackage")
@Namespace("/")
public class ArticleAction extends BaseAction {

	@Autowired
	private ArticleDao articleDao;

	private Article article;

	private List<Article> articles;

	private List<Tag> tags;

	private String tag;

	@Action(value = "admin", results = { @Result(name = "success", type = "freemarker", location = "/WEB-INF/ftl/admin.ftl") })
	public String admin() throws Exception {
		tags = articleDao.findAllTags();
		articles = articleDao.findAllArticles();

		// 修改文章时，根据传递的article.articleId查找文章
		if (article != null) {
			article = articleDao.findArticleById(article.getArticleId());
		}

		return "success";
	}

	@Action(value = "saveArticle", results = {
			@Result(type = "redirectAction", name = "success", params = {
					"actionName",
					"viewArticle?article.articleId=${article.articleId}" }),
			@Result(type = "redirectAction", name = "redirectToAdmin", params = {
					"actionName", "admin" }) })
	public String saveArticle() throws Exception {

		if (!StringUtils.isEmpty(article.getArticleId())) {
			Date date = new Date();
			article.setModifyDate(date);
			if (StringUtils.isEmpty(tag)) {
				article.setTags(null);
			}
			articleDao.update(article);
		} else {
			Date date = new Date();
			article.setCreateDate(date);
			article.setModifyDate(date);
			article.setVisitCount(0);
			article.setArticleId(null);
			articleDao.save(article);
		}

		if (!StringUtils.isEmpty(tag)) {
			article.getTags().clear();
			String[] tags = tag.split("[,|，]");

			for (String tagName : tags) {
				Tag tag = articleDao.findTagByTagName(tagName);
				if (tag == null) {
					tag = new Tag();
					tag.setTagName(tagName);
					tag.setCreateDate(new Date());
					articleDao.saveTag(tag);
				}
				article.getTags().add(tag);
			}
			articleDao.update(article);
		}

		return "success";
	}

	@Action(value = "listArticles", results = { @Result(name = "listArticles", location = "/jsp/article/listArticles.jsp") })
	public String listArticles() throws Exception {
		articles = articleDao.findAllArticles();
		return "listArticles";
	}

//	@Action(value = "viewArticle", results = { @Result(name = "viewArticle", location = "/jsp/article/success.jsp") })
//	public String viewArticle() throws Exception {
//		article = articleDao.findArticleById(article.getArticleId());
//		tags = new ArrayList<Tag>();
//		tags.addAll(article.getTags());
//		return "viewArticle";
//	}
	
	@Action(value = "viewArticle", results = { @Result(name = "success", location = "/WEB-INF/ftl/viewArticle.ftl") })
	public String viewArticle() throws Exception {
		article = articleDao.findArticleById(article.getArticleId());
		tags = new ArrayList<Tag>();
		tags.addAll(article.getTags());
		return "success";
	}
	

	@Action(value = "editArticle", results = { @Result(name = "editArticle", location = "/jsp/index.jsp") })
	public String editArticle() throws Exception {
		article = articleDao.findArticleById(article.getArticleId());
		return "editArticle";
	}

	@Action(value = "deleteArticle", results = {
			@Result(type = "redirectAction", name = "success", params = {
					"actionName", "listArticles" }),
			@Result(type = "redirectAction", name = "redirectToAdmin", params = {
					"actionName", "admin" }) })
	public String deleteArticle() throws Exception {
		article = articleDao.findArticleById(article.getArticleId());
		article.getTags().clear();
		articleDao.delete(article);
		return "redirectToAdmin";
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

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

}
