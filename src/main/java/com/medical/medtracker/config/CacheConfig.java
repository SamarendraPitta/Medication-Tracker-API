package com.medical.medtracker.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@Configuration
@EnableCaching
public class CacheConfig {


 public CacheManager cacheManager() {
     CaffeineCacheManager cacheManager = new CaffeineCacheManager("movingAverages");
     cacheManager.setCaffeine(Caffeine.newBuilder()
         .maximumSize(500)
         .expireAfterWrite(1, TimeUnit.HOURS));
     return cacheManager;
 }
}

