package vttp2022.NUSISS.ssfAssessment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class AppConfig {
     //CONFIGURE REDIS DATABASE

     @Value("${spring.redis.host}")
     private String redisHost;
 
     @Value("${spring.redis.port}")
     private Integer redisPort;
 
     @Value("${spring.redis.database}")
     private Integer redisDatabase;
 
     @Value("${spring.redis.username}")
     private String redisUsername;
 
     @Value("${REDIS_PASSWORD}")
     private String redisPassword;
 
 
 
 //without this,    
 @Bean
 public RedisTemplate<String, Object> initRedisTemplate() {
         
 // create Redis Standalone config
         RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
         redisConfig.setHostName(redisHost);
         redisConfig.setPort(redisPort);
         redisConfig.setDatabase(redisDatabase);
         redisConfig.setUsername(redisUsername);
         redisConfig.setPassword(redisPassword);
 
         System.out.printf("username: %s, password: %s\n", redisUsername, redisPassword);
 
 // Create instance of JEDIS DRIVER
         JedisClientConfiguration jedisConfig = JedisClientConfiguration.builder().build();
 
 // Create a factory for JEDIS Connection        
         JedisConnectionFactory jedisFac = new JedisConnectionFactory(redisConfig, jedisConfig);
         jedisFac.afterPropertiesSet();
   
 
 // Create REDISTEMPLATE
         RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
         redisTemplate.setConnectionFactory(jedisFac);
 
 
         redisTemplate.setKeySerializer (new StringRedisSerializer());
         redisTemplate.setValueSerializer (new StringRedisSerializer());
 
         return redisTemplate;
 
  }
}