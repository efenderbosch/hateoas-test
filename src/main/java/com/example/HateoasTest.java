package com.example;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.context.PersistentEntities;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@EntityScan
@ComponentScan
@EnableAutoConfiguration
public class HateoasTest {

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ConfigurableApplicationContext context = new SpringApplicationBuilder(). //
		sources(HateoasTest.class). //
		run(args);

		DataSource ds = context.getBean(DataSource.class);
		try (
				Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement();) {
			stmt.executeUpdate("insert into parent (id, attribute) values (1, 'attribute')");
			stmt.executeUpdate("insert into child (id, attribute, parent_id) values (1, 'attribute', 1)");
		}
	}

	@Bean
	@Primary
	public RepositoryRestConfiguration repositoryRestConfiguration(PersistentEntities persistentEntities,
			RepositoryRestConfiguration repositoryRestConfiguration) {
		// Force IDs to be serialized.
		for (PersistentEntity<?, ?> persistentEntity : persistentEntities) {
			repositoryRestConfiguration.exposeIdsFor(persistentEntity.getType());
		}
		return repositoryRestConfiguration;
	}
}
