package com.bookstore.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan({"com.bookstore"})
@Configuration
public class SpringRootConfig {

//	@Autowired
//	DataSource dataSource;

//	@Bean
//	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
//		return new NamedParameterJdbcTemplate(dataSource);
//	}
//
//	@PostConstruct
//	public void startDBManager() {
//
//		//hsqldb
//		DatabaseManagerSwing.main(new String[] { "--url", "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });
//	}


}