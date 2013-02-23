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
import com.lichhao.blog.model.Tag;
import com.lichhao.blog.model.User;
import com.lichhao.blog.util.Constants;
import com.lichhao.blog.util.MD5Util;

@ParentPackage("basePackage")
@Namespace("/")
public class ArticleAction extends BaseAction {

	@Autowired
	private ArticleDao articleDao;

	private Article article;

	private Article preArticle;

	private Article nextArticle;

	private List<Article> articles;

	private List<Article> latestArticles;

	private List<Tag> tags;

	private String tag;

	private Tag thisTag;

	private Integer page;

	private Integer total; // 文章总页数

	private Comment comment;

	private List<Comment> latestComments;

	private String replyCommentId;

	private User user;

	@Action(value = "admin", results = {
			@Result(name = "success", type = "freemarker", location = "/WEB-INF/ftl/admin.ftl"),
			@Result(name = "login", location = "/login.jsp") })
	public String admin() throws Exception {

		Object isLogin = session.get("isLogin");

		if (isLogin == null) {
			if (user != null) {
				Boolean isValidate = articleDao.validateUser(user);
				if (isValidate) {
					// request.getSession().setAttribute("isLogin", isValidate);
					session.put("isLogin", isValidate);
				} else {
					request.setAttribute("errorMsg", "用户名密码有错误！请重新输入");
					return "login";
				}
			} else {
				return "login";
			}
		}

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

		if (article.getArticleId()!=null) {

			// 先将改动保存起来
			// TODO:可以优化
			String title = article.getTitle();
			String content = article.getContent();
			String summary = article.getSummary();
			Boolean isPublished = article.getIsPublished();

			article = articleDao.findArticleById(article.getArticleId());

			Date date = new Date();
			article.setModifyDate(date);
			if (StringUtils.isEmpty(tag)) {
				article.setTags(null);
			}

			// 将所做改动反映到数据库中
			article.setContent(content);
			article.setTitle(title);
			article.setSummary(summary);
			article.setIsPublished(isPublished);

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
		latestArticles = articleDao.findLatestArticles();
		Integer articlesTotal = articleDao.getArticlesTotal();
		total = (articlesTotal + Constants.ARTICLES_PER_PAGE - 1)
				/ Constants.ARTICLES_PER_PAGE;

		if (total > 0 && page > total) {
			throw new IllegalStateException("要访问的页数超出范围！");
		}

		latestComments = articleDao.finLatestComments();

		return "success";
	}

	@Action(value = "showTags", results = { @Result(name = "success", location = "/WEB-INF/ftl/showTags.ftl") })
	public String showTags() throws Exception {

		latestArticles = articleDao.findLatestArticles();
		latestComments = articleDao.finLatestComments();
		tags = articleDao.findAllTags();

		return "success";
	}

	@Action(value = "showArticleByTag", results = { @Result(name = "success", location = "/WEB-INF/ftl/showArticleByTag.ftl") })
	public String showArticleByTag() throws Exception {

		latestArticles = articleDao.findLatestArticles();
		latestComments = articleDao.finLatestComments();
		thisTag = articleDao.findTagByTagId(thisTag.getTagId());
		
		return "success";
	}
	@Action(value = "aboutMe", results = { @Result(name = "success", location = "/WEB-INF/ftl/aboutMe.ftl") })
	public String aboutMe() throws Exception {

		latestArticles = articleDao.findLatestArticles();
		latestComments = articleDao.finLatestComments();
		
		return "success";
	}

	@Action(value = "commentArticle", results = { @Result(type = "redirectAction", name = "success", params = {
			"actionName", "viewArticle?article.articleId=${article.articleId}" }) })
	public String commentArticle() throws Exception {

		article = articleDao.findArticleById(article.getArticleId());
		if (StringUtils.isEmpty(replyCommentId)) {
			comment.setCreateDate(new Date());
			comment.setCommentEmail(MD5Util.MD5(comment.getCommentEmail()));
			article.getComments().add(comment);
			article = articleDao.update(article);
		} else {
			Comment replyComment = articleDao.findCommentById(replyCommentId);
			comment.setCreateDate(new Date());
			comment.setCommentEmail(MD5Util.MD5(comment.getCommentEmail()));
			replyComment.getSubComments().add(comment);
			articleDao.updateComment(replyComment);
		}

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
		latestArticles = articleDao.findLatestArticles();
		latestComments = articleDao.finLatestComments();
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

	public String getReplyCommentId() {
		return replyCommentId;
	}

	public void setReplyCommentId(String replyCommentId) {
		this.replyCommentId = replyCommentId;
	}

	public List<Article> getLatestArticles() {
		return latestArticles;
	}

	public void setLatestArticles(List<Article> latestArticles) {
		this.latestArticles = latestArticles;
	}

	public List<Comment> getLatestComments() {
		return latestComments;
	}

	public void setLatestComments(List<Comment> latestComments) {
		this.latestComments = latestComments;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Tag getThisTag() {
		return thisTag;
	}

	public void setThisTag(Tag thisTag) {
		this.thisTag = thisTag;
	}

}
