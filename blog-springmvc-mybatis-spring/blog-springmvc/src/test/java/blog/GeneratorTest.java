package blog;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import blog.dao.CommentMapper;
import blog.model.Comment;
import blog.model.CommentExample;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@ActiveProfiles("mysql-blogtest")
public class GeneratorTest {
	
	@Autowired
	private CommentMapper commentMapper;

	@Test
	public void testBlogComment() throws Exception{
		
		CommentExample example = new CommentExample();
		List<Comment> comments = commentMapper.selectByExampleWithBLOBs(example);
		assertThat(comments.size(), is(24));
		
		comments = commentMapper.selectByExampleWithBLOBs(example);
		assertThat(comments.size(), is(24));
		comments = commentMapper.selectByExampleWithBLOBs(example);
		assertThat(comments.size(), is(24));
		comments = commentMapper.selectByExampleWithBLOBs(example);
		assertThat(comments.size(), is(24));
		
		example.createCriteria().andCommentIdEqualTo("2fa998b23a4d666f013a4d6af8fb0000").andEmailEqualTo("4543d381c252c70925caa58a2921c347");
		
		comments = commentMapper.selectByExampleWithBLOBs(example);
		assertThat(comments.size(), is(1));
		comments = commentMapper.selectByExampleWithBLOBs(example);
		assertThat(comments.size(), is(1));
		comments = commentMapper.selectByExampleWithBLOBs(example);
		assertThat(comments.size(), is(1));
		comments = commentMapper.selectByExampleWithBLOBs(example);
		assertThat(comments.size(), is(1));
		
		Comment comment = commentMapper.selectByPrimaryKey("2fa998b23a4d666f013a4d6af8fb0000");
		assertEquals("2fa998b23a4d666f013a4d6af8fb0000", comment.getCommentId());
		
	}
	
	
}
