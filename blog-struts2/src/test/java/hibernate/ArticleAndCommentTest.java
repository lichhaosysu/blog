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
import com.lichhao.blog.model.Comment;

/**
 * Illustrates the use of Hibernate native APIs. The code here is unchanged from
 * the {@code basic} example, the only difference being the use of annotations
 * to supply the metadata instead of Hibernate mapping files.
 * 
 * @author Steve Ebersole
 */
public class ArticleAndCommentTest {
	private SessionFactory sessionFactory;

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void test() throws Exception {
		// 基于注解的SessionFactory
		createAnnotataionSessionFactory();
		testArticleAndComment();
		closeSessionFactory();

		// 基于Xml文件的SessionFactory
		createXmlSessionFactory();
		testArticleAndComment();
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

	public void testArticleAndComment() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

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
		session.save(article);
		assertNotNull("持久化后Article实例持久化标识不为空！", article.getArticleId());

		Comment comment = new Comment();
		comment.setCommentContent("这是一个测试评论1");
		comment.setCommentName("lichhao");
		comment.setCommentEmail("lichhaosysu@gmail.com");
		comment.setCommentUrl("http://lichhao.com/blog");
		comment.setCreateDate(new Date());

		comment.setArticle(article);
		session.save(comment);

		Comment comment3 = new Comment();
		comment3.setCommentContent("这是一个测试评论3");
		comment3.setCommentName("lichhao3");
		comment3.setCommentEmail("lichhaosysu@gmail.com");
		comment3.setCommentUrl("http://lichhao.com/blog");
		comment3.setCreateDate(new Date());

		comment3.setArticle(article);
		session.save(comment3);

		Comment comment2 = new Comment();
		comment2.setCommentContent("这是一个测试评论2");
		comment2.setCommentName("lichhao");
		comment2.setCommentEmail("lichhaosysu@gmail.com");
		comment2.setCommentUrl("http://lichhao.com/blog");
		comment2.setCreateDate(new Date());
		session.save(comment2);

		Comment comment4 = new Comment();
		comment4.setCommentContent("这是一个测试评论4");
		comment4.setCommentName("lichhao");
		comment4.setCommentEmail("lichhaosysu@gmail.com");
		comment4.setCommentUrl("http://lichhao.com/blog");
		comment4.setCreateDate(new Date());
		session.save(comment4);

		comment.getSubComments().add(comment2);
		comment.getSubComments().add(comment4);

		session.getTransaction().commit();
		session.close();

		session = sessionFactory.openSession();
		session.beginTransaction();

		Article obj = (Article) session.load(Article.class,
				article.getArticleId());
		logger.debug("文章标识：" + obj.getArticleId());
		logger.debug("文章标题：" + obj.getContent());
		logger.debug("文章评论：");
		logger.debug("-------------------------------------");
		for (Comment com : obj.getComments()) {
			logger.debug("评论标识：" + com.getCommentId());
			logger.debug("评论内容：" + com.getCommentContent());
			logger.debug("评论回复：");
			logger.debug("------------------------");
			for (Comment com2 : com.getSubComments()) {
				logger.debug("评论标识：" + com2.getCommentId());
				logger.debug("评论内容：" + com2.getCommentContent());
			}
			logger.debug("------------------------");
		}
		logger.debug("-------------------------------------");

		session.delete(obj);
		// session.delete(obj.getComments().get(0));

		session.getTransaction().commit();
		session.close();
	}
}
