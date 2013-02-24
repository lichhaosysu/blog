package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-dataSource.xml" })
public class BaseHibernateConfig {
	
	protected SessionFactory sessionFactory;

	protected Logger logger = LoggerFactory.getLogger(getClass());

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

}
