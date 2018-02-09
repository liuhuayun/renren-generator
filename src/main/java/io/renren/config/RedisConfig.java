/*package io.renren.config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;

  @Value("${spring.redis.timeout}")
  private int timeout;

  @Value("${spring.redis.password}")
  private String password;

  @Value("${spring.redis.database}")
  private int database;

  @Value("${spring.redis.pool.max-idle}")
  private int maxIdle;

  @Value("${spring.redis.pool.min-idle}") 
  private int minIdle;

  *//**
   *  注解@Cache key生成规则
   *//*
  @Bean
  public KeyGenerator keyGenerator() {
      return new KeyGenerator() {
        @Override
        public Object generate(Object target, Method method, Object... params) {
             StringBuilder sb = new StringBuilder();
             sb.append(target.getClass().getName());
             sb.append(method.getName());
             for (Object obj : params) {
                 sb.append(obj.toString());
             }
             return sb.toString();
        }
      };
  }

  *//**
   *  注解@Cache的管理器，设置过期时间的单位是秒
   * @Description:
   * @param redisTemplate
   * @return
   *//*
  @Bean
  public CacheManager cacheManager(RedisTemplate redisTemplate) {
      RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
      Map<String, Long> expires=new HashMap<String, Long>();
      expires.put("user", 6000L);
      expires.put("city", 600L);
      cacheManager.setExpires(expires);
      // Number of seconds before expiration. Defaults to unlimited (0)
      cacheManager.setDefaultExpiration(600); //设置key-value超时时间
     return cacheManager;
  }

  *//**
   * redis模板，存储关键字是字符串，值是Jdk序列化
   * @Description:
   * @param factory
   * @return
   *//*
  @Bean
  public RedisTemplate redisTemplate(RedisConnectionFactory factory) {
      RedisTemplate template = new RedisTemplate<>();
      template.setConnectionFactory(factory);
     // Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
     StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
     template.setDefaultSerializer(stringRedisSerializer);
     template.setKeySerializer(stringRedisSerializer);
     template.setHashKeySerializer(stringRedisSerializer);
      return template;
  }


  *//**
   * redis连接的基础设置
   * @Description:
   * @return
   *//*
  @Bean
  public JedisConnectionFactory redisConnectionFactory() {
    JedisConnectionFactory factory = new JedisConnectionFactory();
    factory.setHostName(host);
    factory.setPort(port);
    factory.setPassword(password);
    //存储的库
    factory.setDatabase(database);
    //设置连接超时时间
    factory.setTimeout(timeout); 
    factory.setUsePool(true);
    factory.setPoolConfig(jedisPoolConfig());
    return factory;
  }

  *//**
   * 连接池配置
   * @Description:
   * @return
   *//*
  @Bean
  public JedisPoolConfig jedisPoolConfig() {
    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
    jedisPoolConfig.setMaxIdle(maxIdle);
    jedisPoolConfig.setMinIdle(minIdle);
//    jedisPoolConfig.set ...
    return jedisPoolConfig;
  }

  *//**
   * redis数据操作异常处理
   * 这里的处理：在日志中打印出错误信息，但是放行
   * 保证redis服务器出现连接等问题的时候不影响程序的正常运行，使得能够出问题时不用缓存
   * @return
   *//*
  @Bean
  @Override
  public CacheErrorHandler errorHandler() {
      CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
          @Override
          public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
              logger.error("redis异常：key=[{}]",key,e);
          }

          @Override
          public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
              logger.error("redis异常：key=[{}]",key,e);
          }

          @Override
          public void handleCacheEvictError(RuntimeException e, Cache cache, Object key)    {
              logger.error("redis异常：key=[{}]",key,e);
          }

          @Override
          public void handleCacheClearError(RuntimeException e, Cache cache) {
              logger.error("redis异常：",e);
          }
      };
      return cacheErrorHandler;
  }

}
*/