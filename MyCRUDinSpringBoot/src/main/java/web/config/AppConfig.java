//package web.config;
//
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import javax.sql.DataSource;
//import java.util.Properties;
//
//@Configuration
//@PropertySource("classpath:application.properties")
//@EnableJpaRepositories(basePackages = "web.dao")
//@EnableTransactionManagement
//@EnableWebMvc
//@ComponentScan(basePackages = "web")
//public class AppConfig {
//
//   @Autowired
//   private Environment env;
//
//   @Bean
//   public DataSource getDataSource() {
//      HikariConfig hikariConfig = new HikariConfig();
//      hikariConfig.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//      hikariConfig.setJdbcUrl(env.getProperty("spring.datasource.url"));
//      hikariConfig.setUsername(env.getProperty("spring.datasource.username"));
//      hikariConfig.setPassword(env.getProperty("spring.datasource.password"));
//      return new HikariDataSource(hikariConfig);
//   }
//
//   @Bean
//   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//      em.setDataSource(getDataSource());
//      em.setPackagesToScan("web.model");
//
//      HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//      em.setJpaVendorAdapter(vendorAdapter);
//
//      Properties props = new Properties();
//      props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//      props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//
//      em.setJpaProperties(props);
//      return em;
//   }
//
//   @Bean
//   public JpaTransactionManager transactionManager() {
//      JpaTransactionManager transactionManager = new JpaTransactionManager();
//      transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
//      return transactionManager;
//   }
//
//
//}