package com.business.cybord.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.business.cybord.config.properties.DataBaseConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
public class RepositoryContext {
	
	@Autowired
	private DataBaseConfig cloud;
	
	@Bean(name = "cajaDatasource")
	public HikariDataSource getDatasource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).url(cloud.getDataSourceUrl())
				.driverClassName(cloud.getDataSourceClassName()).username(cloud.getDataSourceUser())
				.password(cloud.getDataSourcePass()).build();
	}

	@Bean(name = "cajaManagerTemplate")
	@Autowired
	public JdbcTemplate getTemplate(@Qualifier("cajaDatasource") DataSource dsSlave) {
		return new JdbcTemplate(dsSlave);
	}

}
