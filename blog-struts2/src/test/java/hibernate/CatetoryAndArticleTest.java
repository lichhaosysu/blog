/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * Copyright (c) 2010, Red Hat Inc. or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Inc.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 */
package hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.lichhao.blog.model.Article;
import com.lichhao.blog.model.Category;

/**
 * Illustrates the use of Hibernate native APIs. The code here is unchanged from
 * the {@code basic} example, the only difference being the use of annotations
 * to supply the metadata instead of Hibernate mapping files.
 * 
 * @author Steve Ebersole
 */
public class CatetoryAndArticleTest extends BaseHibernateConfig {

	@Test
	public void test() throws Exception {
		// 基于注解的SessionFactory
		createAnnotataionSessionFactory();
		testCategoryAndArticle();
		closeSessionFactory();

		// 基于Xml文件的SessionFactory
		createXmlSessionFactory();
		testCategoryAndArticle();
		closeSessionFactory();
	}

	public void testCategoryAndArticle() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Category category = new Category();
		category.setCatName("未分类");
		category.setCreateDate(new Date());
		session.save(category);// 立即执行sql insert，持久化标识立即可用
		assertNotNull("持久化后Category实例持久化标识不为空！", category.getCatId());

		// session.persist(category);// 可能会延迟执行sql insert，持久化标识可能等到flush才可用

		Article article = new Article();
		// 控制权在多方时
		// hibernate的双向关系，控制权一般在多方
		article.setCategory(category);

		article.setTitle("测试标题");
		article.setContent("这是一个测试的文章");
		article.setCreateDate(new Date());
		article.setModifyDate(new Date());
		article.setSummary("测试摘要");
		article.setVisitCount(0);
		article.setIsPublished(Boolean.TRUE);
		article.setType("post");
		article.setCommentStatus("open");
		article.setArticleStatus("public");
		article.setLink("http://localhost:8080/newblog/post/abce");
		session.save(article);
		assertNotNull("持久化后Article实例持久化标识不为空！", article.getArticleId());
		// 控制权在一方时
		// category.getArticles().add(article);
		// session.update(category);

		Query createQuery = session
				.createQuery("from Category c where c.catId = :catId");
		createQuery.setParameter("catId", category.getCatId());

		Category cat = (Category) createQuery.uniqueResult();
		// 为防止hibernate缓存，重新从数据库加载数据
		session.refresh(cat);

		assertEquals(cat.getCatId(), category.getCatId());
		assertEquals(cat.getCatName(), category.getCatName());
		assertEquals(cat.getCreateDate(), category.getCreateDate());

		logger.debug("类别标识：" + cat.getCatId());
		logger.debug("类别名称：" + cat.getCatName());
		logger.debug("文章：");
		List<Article> articles = cat.getArticles();
		for (Article bean : articles) {
			logger.debug("------------");
			logger.debug("文章标识：" + bean.getArticleId());
			logger.debug("文章标题：" + bean.getTitle());
			logger.debug("文章内容：" + bean.getContent());
			logger.debug("------------");
		}

		for (Article bean : cat.getArticles()) {
			session.delete(bean); // 有外键限制，要先删除所有该分类下的文章
		}

		session.delete(cat);
		session.getTransaction().commit();
		session.close();
	}
}
