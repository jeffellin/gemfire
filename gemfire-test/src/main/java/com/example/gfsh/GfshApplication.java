package com.example.gfsh;

import com.example.gfsh.config.GeodeConfig;
import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.RegionShortcut;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GfshApplication  implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(GfshApplication.class, args);
	}

	@Autowired
	GeodeConfig geodeConfig;

	@Bean
	public Cache cache()
	{
		//ClientCache cache = new ClientCacheFactory()
		//		.setPdxSerializer(new ReflectionBasedAutoSerializer("com.example.gfsh.model.*"))
		//		.create();

		return  CacheFactory.getAnyInstance();
	}

	@Bean
	public Region fooRegion(Cache cache){

		Region region =
				cache.createRegionFactory(RegionShortcut.REPLICATE).create("foo");


		return region;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {


		geodeConfig.doSomething();

	}
}


