package mybatis;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.text.SimpleDateFormat;

import mybatis.mapper.TestMapper;
import mybatis.model.TestBean;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@ActiveProfiles("hsqldb-simpletest")
public class MybatisSimpleTest {

	@Autowired
	private TestMapper testMapper;

	@Test
	public void testPlainMybatisUsage() throws Exception {
		String resource = "mybatis/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		try {
			TestBean param = new TestBean();
			param.setName("lichhao");
			param.setDescription("Description for lichhao");
			param.setCreateDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2012-10-29 00:00:00"));
			
			TestBean testBean = session.selectOne(
					"mybatis.mapper.TestMapper.selectTest", param);
			assertEquals("Description for lichhao", testBean.getDescription());
			assertEquals("2012-10-29",
					new SimpleDateFormat("yyyy-MM-dd").format(testBean
							.getCreateDate()));

			TestMapper mapper = session.getMapper(TestMapper.class);
			param.setName("lichhao2");
			param.setDescription("Description for lichhao2");
			param.setCreateDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2012-10-29 15:22:50"));
			
			testBean = mapper.selectTest(param);
			assertEquals("Description for lichhao2", testBean.getDescription());
			assertEquals("2012-10-29 15:22:50", new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(testBean.getCreateDate()));
		} finally {
			session.close();
		}

	}

	@Test
	public void testMybatisSpringUsage() {
		TestBean param = new TestBean();
		param.setName("lichhao");
		TestBean testBean = testMapper.selectTest(param);
		assertEquals("Description for lichhao", testBean.getDescription());
		assertEquals("2012-10-29",
				new SimpleDateFormat("yyyy-MM-dd").format(testBean
						.getCreateDate()));
	}
}
