package com.lijq.mybatis.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @author Lijq
 * @date 2018/3/28 9:41
 * @description
 */
@Configuration
@ComponentScan(value = "com.lijq.mybatis")
@PropertySource(value = "classpath:jdbc.properties")
public class DataSourcesConfiguration {

    @Value(value = "${jdbc.driverClassName}")
    private String driverClassName;

    @Value(value = "${jdbc.url}")
    private String url;

    @Value(value = "${jdbc.username}")
    private String username;

    @Value(value = "${jdbc.password}")
    private String password;

    @Bean
    public Filter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(2000);
        statFilter.setLogSlowSql(true);
        return statFilter;
    }

    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();

        // basic settings
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        // 配置初始化大小，最小，最大
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);

        // 配置获取连接等待超时时间
        dataSource.setMaxWait(60000);

        // 配置间隔多久进行一次检查，检查需要关闭的空闲连接，单位毫秒
        dataSource.setTimeBetweenConnectErrorMillis(60000);

        // 配置一个连接在池中最小生存时间，单位毫秒
        dataSource.setMinEvictableIdleTimeMillis(300000);

        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);

        // 打开PSCache，并指定每个连接上的PSCache大小
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        // 配置监控统计拦截的filters
        //dataSource.setFilters("stat, config");
        dataSource.setProxyFilters(Arrays.asList(statFilter()));

        // 开启数据库密码加密功能
        //dataSource.setConnectionProperties("config.decrypt=true");
        return dataSource;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer loadProperties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        return configurer;
    }

}
