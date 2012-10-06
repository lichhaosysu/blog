package com.lichhao.blog.action;

import java.net.URLEncoder;
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
import com.lichhao.blog.model.Comment;
import com.lichhao.blog.util.Constants;
import com.lichhao.blog.util.MD5Util;
import com.lichhao.blog.model.Tag;

@ParentPackage("basePackage")
@Namespace("/")
public class ArticleAction extends BaseAction {

	@Autowired
	private ArticleDao articleDao;

	private Article article;

	private Article preArticle;

	private Article nextArticle;

	private List<Article> articles;

	private List<Tag> tags;

	private String tag;

	private Integer page;

	private Integer total; // 文章总页数

	private Comment comment;

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

	@Action(value = "index", results = { @Result(name = "success", location = "/WEB-INF/ftl/index.ftl") })
	public String listArticles() throws Exception {

		if (page == null) {
			page = 1; // 默认第一页
		}

		articles = articleDao.findArticlesByPage(page);
		Integer articlesTotal = articleDao.getArticlesTotal();
		total = (articlesTotal + Constants.ARTICLES_PER_PAGE - 1)
				/ Constants.ARTICLES_PER_PAGE;

		if (total > 0 && page > total) {
			throw new IllegalStateException("要访问的页数超出范围！");
		}

		return "success";
	}

	@Action(value = "commentArticle", results = { @Result(type = "redirectAction", name = "success", params = {
			"actionName", "viewArticle?article.articleId=${article.articleId}" }) })
	public String commentArticle() throws Exception {

		article = articleDao.findArticleById(article.getArticleId());
		comment.setCreateDate(new Date());
		comment.setEmail(MD5Util.MD5(comment.getEmail()));

		article.getComments().add(comment);
		article = articleDao.update(article);

		// request.getRequestDispatcher("viewArticle.action?article.articleId="+article.getArticleId()+"#comments").forward(request,
		// response);

		response.sendRedirect("viewArticle.action?article.articleId="
				+ article.getArticleId() + "#comments");

		return NONE;
	}

	@Action(value = "viewArticle", results = { @Result(name = "success", location = "/WEB-INF/ftl/viewArticle.ftl") })
	public String viewArticle() throws Exception {
		request.setAttribute("default_person_icon", URLEncoder.encode(
				"http://lichhao.com/blog/img/default-person.png", "utf-8"));
		article = articleDao.findArticleById(article.getArticleId());
		article.setVisitCount(article.getVisitCount() + 1);
		
		article = articleDao.update(article);
		preArticle = articleDao.findPreArticle(article);
		nextArticle = articleDao.findNextArticle(article);
		
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

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public Article getPreArticle() {
		return preArticle;
	}

	public void setPreArticle(Article preArticle) {
		this.preArticle = preArticle;
	}

	public Article getNextArticle() {
		return nextArticle;
	}

	public void setNextArticle(Article nextArticle) {
		this.nextArticle = nextArticle;
	}

}
