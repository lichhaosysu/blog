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

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lichhao.blog.model.Article;
import com.lichhao.blog.model.Tag;

/**
 * Illustrates the use of Hibernate native APIs. The code here is unchanged from
 * the {@code basic} example, the only difference being the use of annotations
 * to supply the metadata instead of Hibernate mapping files.
 * 
 * @author Steve Ebersole
 */
public class TagAndArticleTest {
	private SessionFactory sessionFactory;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void test() throws Exception {
		// 基于注解的SessionFactory
		createAnnotataionSessionFactory();
		testTagAndArticle();
		closeSessionFactory();

		// 基于Xml文件的SessionFactory
		createXmlSessionFactory();
		testTagAndArticle();
		closeSessionFactory();
	}

	public void createAnnotataionSessionFactory() throws Exception {
		// A SessionFactory is set up once for an application
		// configures settings from the hibernate.cfg.xml located in root
		// classpath

		Configuration cfg = new Configuration();
		cfg.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(cfg.getProperties()).buildServiceRegistry();
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	}

	public void createXmlSessionFactory() throws Exception {
		// A SessionFactory is set up once for an application
		// configures settings from the hibernate.cfg.xml located in root
		// classpath

		Configuration cfg = new Configuration();
		cfg.configure("hbm/hibernate.cfg.xml");
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
				.applySettings(cfg.getProperties()).buildServiceRegistry();
		sessionFactory = cfg.buildSessionFactory(serviceRegistry);
	}

	public void closeSessionFactory() throws Exception {
		if (sessionFactory != null) {
			sessionFactory.close();
		}
	}

	public void testTagAndArticle() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Tag tag = new Tag();
		tag.setTagName("未分类");
		tag.setCreateDate(new Date());
		session.save(tag);
		assertNotNull("持久化后Tag实例持久化标识不为空！", tag.getTagId());

		Tag tag1 = new Tag();
		tag1.setTagName("生活感悟");
		tag1.setCreateDate(new Date());
		session.save(tag1);
		assertNotNull("持久化后Tag实例持久化标识不为空！", tag1.getTagId());

		Tag tag2 = new Tag();
		tag2.setTagName("职业生涯");
		tag2.setCreateDate(new Date());
		session.save(tag2);
		assertNotNull("持久化后Tag实例持久化标识不为空！", tag2.getTagId());

		Tag tag3 = new Tag();
		tag3.setTagName("胡言乱语");
		tag3.setCreateDate(new Date());
		session.save(tag3);
		assertNotNull("持久化后Tag实例持久化标识不为空！", tag3.getTagId());

		Article article = new Article();
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

		Set<Tag> ts = article.getTags();
		ts.add(tag);
		ts.add(tag1);
		ts.add(tag2);
		ts.add(tag3);

		session.save(article);
		assertNotNull("持久化后Article实例持久化标识不为空！", article.getArticleId());

		article = new Article();
		article.setTitle("测试标题2");
		article.setContent("这是一个测试的文章2");
		article.setCreateDate(new Date());
		article.setModifyDate(new Date());
		article.setSummary("测试摘要2");
		article.setVisitCount(0);
		article.setIsPublished(Boolean.TRUE);
		article.setType("post");
		article.setCommentStatus("open");
		article.setArticleStatus("public");
		article.setLink("http://localhost:8080/newblog/post/abce2");

		ts = article.getTags();
		ts.add(tag);
		ts.add(tag1);
		ts.add(tag2);
		ts.add(tag3);

		session.save(article);
		assertNotNull("持久化后Article实例持久化标识不为空！", article.getArticleId());

		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
		session.beginTransaction();

		List<Article> articles = session
				.createQuery(
						"select p from Article p left join fetch p.tags t where t.tagName = :tagName order by p.createDate desc")
				.setParameter("tagName", tag.getTagName()).list();
		logger.debug("与'" + tag.getTagName() + "'相关的帖子:");
		for (Article bean : articles) {
			session.refresh(bean);
			logger.debug("------------------------");
			logger.debug("文章标识：" + bean.getArticleId());
			logger.debug("文章标题：" + bean.getTitle());
			logger.debug("所属标签：");
			for (Tag t : bean.getTags()) {
				logger.debug(t.getTagName());
			}
			logger.debug("------------------------");
		}

		articles = session.createQuery("from Article").list();
		for (Article bean : articles) {
			session.delete(bean);
		}

		List<Tag> tags = session.createQuery("from Tag").list();
		for (Tag bean : tags) {
			session.delete(bean);
		}

		session.getTransaction().commit();
		session.close();
	}
}
