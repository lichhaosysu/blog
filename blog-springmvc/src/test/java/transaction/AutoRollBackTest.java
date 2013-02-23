package transaction;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
@ActiveProfiles("hsqldb-rollbacktest")
public class AutoRollBackTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	@Rollback(true)
	public void testAutoRollBack() {
		String sql = "insert into my_test values (1,'lichhao')";
		jdbcTemplate.execute(sql);
	}
}
