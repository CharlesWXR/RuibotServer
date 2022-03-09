package edu.njnu.ruibot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {
	private Logger logger = LoggerFactory.getLogger(DruidConfig.class);

	@Bean("dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource getDataSource() {
		return new DruidDataSource();
	}
}
