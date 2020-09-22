package com.cssl.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tym on 2018/8/27 0027.
 * Redis缓存配置类
 * 该缓存配置不支持redis2.x.x
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    //默认缓存管理器
    /*@Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        RedisCacheManager cacheManager = RedisCacheManager.create(factory);
        return cacheManager;
    }*/

    //缓存管理器
    @Bean
    public CacheManager cacheManager2(RedisConnectionFactory connectionFactory) {

        //反序列化
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance ,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        serializer.setObjectMapper(om);

        //设置缓存配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        RedisCacheConfiguration config1 = config.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(serializer))
                .entryTtl(Duration.ofSeconds(300))   //缓存时间,秒
                .disableCachingNullValues()         //不缓存空值
                .prefixKeysWith("user");             //去掉::

        RedisCacheConfiguration config2 = config.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(serializer))
                .entryTtl(Duration.ofSeconds(60))   //缓存时间,秒
                .disableCachingNullValues()        //不缓存空值
                .prefixKeysWith("news");            //缓存区间

        //对每个缓存空间应用不同的配置
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("user", config1);
        configMap.put("news", config2);

        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);

        //初始化RedisCacheManager
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, config, configMap);
        return cacheManager;
    }
}
