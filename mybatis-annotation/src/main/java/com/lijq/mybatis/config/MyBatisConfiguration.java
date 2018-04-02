package com.lijq.mybatis.config;

import com.github.pagehelper.PageInterceptor;
import com.github.pagehelper.QueryInterceptor;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Lijq
 * @date 2018/3/28 9:38
 * @description
 */
@Configuration
@MapperScan(basePackages = {"classpath:mybatis", "com.lijq.mybatis.mapper"})
@EnableTransactionManagement(proxyTargetClass = true)
public class MyBatisConfiguration {

    @Autowired
    @Qualifier("dataSource")
    public DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        // mapper.xml path
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mybatis/*.xml"));

        // typeHandler
        //sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{});
        //sqlSessionFactoryBean.setTypeHandlersPackage("com.lijq.mybatis");

        // plugins
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor(), new QueryInterceptor()});

        // factory
        SqlSessionFactory factory = sqlSessionFactoryBean.getObject();

        // 懒加载
        /*factory.getConfiguration().setLazyLoadingEnabled(true);
        factory.getConfiguration().setAggressiveLazyLoading(false);
        factory.getConfiguration().setProxyFactory(new CglibProxyFactory());*/

        // 日志
        factory.getConfiguration().setLogImpl(Slf4jImpl.class);

        return factory;
    }

    private PageInterceptor pageInterceptor() {
        Properties properties = new Properties();
        properties.put("helperDialect", "mysql");
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    @Primary
    @Lazy(value = false)
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(), ExecutorType.SIMPLE);
    }

    @Lazy(value = false)
    @Bean(name = "batchSqlSessionTemplate")
    public SqlSessionTemplate batchSqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory(), ExecutorType.BATCH);
    }

    @Bean(name = "txManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
