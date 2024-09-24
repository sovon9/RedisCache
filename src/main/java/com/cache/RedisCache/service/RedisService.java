package com.cache.RedisCache.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class RedisService
{
	@Autowired
	private RedisTemplate<String,String> redisTemplate;
	
	public void set(String key, Object v, long timeout)
	{
			ObjectMapper objectnapper=new ObjectMapper();
			try
			{
				objectnapper.registerModule(new JavaTimeModule());
				String valueAsString = objectnapper.writeValueAsString(v);
				redisTemplate.opsForValue().set(key, valueAsString, timeout, TimeUnit.SECONDS);
				
			} catch (JsonProcessingException e)
			{
				e.printStackTrace();
			}
	}
	
	public <T> T get(String key, Class<T> entity)
	{
			ObjectMapper objectnapper=new ObjectMapper();
			try
			{
				objectnapper.registerModule(new JavaTimeModule());
				String value = redisTemplate.opsForValue().get(key);
				if (null != value)
				{
					return objectnapper.readValue(value, entity);
				} else
				{
					return null;
				}
				
			} catch (JsonProcessingException e)
			{
				e.printStackTrace();
				return null;
			}
	}
}
