package dao.impl;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.ogm.OgmSessionFactory;
import org.hibernate.ogm.boot.OgmSessionFactoryBuilder;
import org.hibernate.ogm.cfg.OgmProperties;

public class MySessionFactory {

	private static MySessionFactory instance = null;
	private OgmSessionFactory sessionFactory;

	private MySessionFactory() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.applySetting(OgmProperties.ENABLED, true).configure().build();

		Metadata meta = new MetadataSources(registry).addAnnotatedClass(entity.Address.class)
				.addAnnotatedClass(entity.User.class).addAnnotatedClass(entity.News.class)
				.addAnnotatedClass(entity.Comment.class).getMetadataBuilder().build();

		sessionFactory = meta.getSessionFactoryBuilder().unwrap(OgmSessionFactoryBuilder.class).build();
	}

	public static synchronized MySessionFactory getInstance() {
		if (instance == null)
			instance = new MySessionFactory();
		return instance;
	}

	public OgmSessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
