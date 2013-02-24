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

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.Test;

import com.lichhao.blog.model.Article;
import com.lichhao.blog.model.Category;
import com.lichhao.blog.model.Comment;
import com.lichhao.blog.model.Tag;

/**
 * Illustrates the use of Hibernate native APIs. The code here is unchanged from
 * the {@code basic} example, the only difference being the use of annotations
 * to supply the metadata instead of Hibernate mapping files.
 * 
 * @author Steve Ebersole
 */
public class AllInOneTablesTest extends BaseHibernateConfig {

	@Test
	public void test() throws Exception {
		// 基于注解的SessionFactory
		createAnnotataionSessionFactory();
		testTables();
		closeSessionFactory();

		// 基于Xml文件的SessionFactory
		createXmlSessionFactory();
		testTables();
		closeSessionFactory();
	}

	public void testTables() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		// 创建一个分类
		final Category category = new Category();
		category.setCatName("未分类");
		category.setCreateDate(new Date());
		session.save(category);// 立即执行sql insert，持久化标识立即可用
		assertNotNull("持久化后Category实例持久化标识不为空！", category.getCatId());

		// 创建一篇文章
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

		// 创建一些标签
		Tag tag0 = new Tag();
		tag0.setTagName("未分类");
		tag0.setCreateDate(new Date());
		session.save(tag0);
		assertNotNull("持久化后Tag实例持久化标识不为空！", tag0.getTagId());

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

		// 为文章打上标签
		Set<Tag> ts = article.getTags();
		ts.add(tag0);
		ts.add(tag1);
		ts.add(tag2);
		ts.add(tag3);

		// 创建一些评论
		Comment comment0 = new Comment();
		comment0.setCommentContent("这是一个测试评论1");
		comment0.setCommentName("lichhao");
		comment0.setCommentEmail("lichhaosysu@gmail.com");
		comment0.setCommentUrl("http://lichhao.com/blog");
		comment0.setCreateDate(new Date());

		comment0.setArticle(article);
		session.save(comment0);

		Comment comment2 = new Comment();
		comment2.setCommentContent("这是一个测试评论2");
		comment2.setCommentName("lichhao");
		comment2.setCommentEmail("lichhaosysu@gmail.com");
		comment2.setCommentUrl("http://lichhao.com/blog");
		comment2.setCreateDate(new Date());
		session.save(comment2);

		Comment comment3 = new Comment();
		comment3.setCommentContent("这是一个测试评论3");
		comment3.setCommentName("lichhao3");
		comment3.setCommentEmail("lichhaosysu@gmail.com");
		comment3.setCommentUrl("http://lichhao.com/blog");
		comment3.setCreateDate(new Date());

		comment3.setArticle(article);
		session.save(comment3);

		Comment comment4 = new Comment();
		comment4.setCommentContent("这是一个测试评论4");
		comment4.setCommentName("lichhao");
		comment4.setCommentEmail("lichhaosysu@gmail.com");
		comment4.setCommentUrl("http://lichhao.com/blog");
		comment4.setCreateDate(new Date());
		session.save(comment4);

		Set<Comment> subComments = comment0.getSubComments();
		subComments.add(comment2);
		subComments.add(comment4);

		session.getTransaction().commit();
		session.close();

		// 清理数据
		logger.info("开始清理数据");
		session = sessionFactory.openSession();
		session.beginTransaction();

		Article obj = (Article) session.load(Article.class,
				article.getArticleId());

		session.delete(obj);
		session.flush();
		Iterator<?> iterate = session.createQuery("from Tag").iterate();
		Tag t = null;
		while (iterate.hasNext()) {
			t = (Tag) iterate.next();
			session.delete(t);
			session.flush();
			session.evict(t);
		}

		// hibernate用于执行执行sql语句的机制，还有doReturningWork
		session.doWork(new Work() {

			@Override
			public void execute(Connection connection) throws SQLException {
				Statement stmt = connection.createStatement();
				stmt.executeUpdate("delete from category where cat_id = '"
						+ category.getCatId() + "'");
				stmt.close();
			}
		});
		session.getTransaction().commit();
		session.close();
		logger.info("清理数据结束");
	}

}
