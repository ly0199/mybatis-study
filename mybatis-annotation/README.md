# mybatis-annotation
MyBatis 注解形式使用

- 在测试时如果测试方法不加`@Transactional`注解时，日志会打印`xxxx will not be managed by Spring`，根据日志信息展示也是使用2个不同的SqlSession，此时一级缓存无效

- `com.lijq.mybatis.config.MyBatisConfiguration` 中引入具体配置相关内容