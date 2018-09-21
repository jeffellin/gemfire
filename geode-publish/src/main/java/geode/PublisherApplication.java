package com.example.gemfirehack;

import com.example.gemfirehack.model.Track;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.Random;

@SpringBootApplication
public class GemfireHackApplication {

	public static void main(String[] args) {
		SpringApplication.run(GemfireHackApplication.class, args);
	}

	@Bean
	ApplicationRunner runner(Region trackRegion) {

		return args -> {


			while(true) {
	  			//Long pos = new Random().nextLong();

       			trackRegion.put(2L, new Track().setId(1L).setName("B2").setPosition(System.currentTimeMillis()));

       			Thread.sleep(1000);

			}


		};
		}



	@Bean
	public ClientCache cache(){

		ClientCache cache = new ClientCacheFactory().addPoolLocator("127.0.0.1", 10334)
				.setPdxSerializer(new ReflectionBasedAutoSerializer("com.example.gemfirehack.model.Track"))
				.setPdxReadSerialized(true).set("log-level", "WARN").create();

		return cache;

	}


	@Bean
	public Region trackRegion(ClientCache cache){
		// create a region on the server
		Region<Long, Track> region =
				cache.<Long, Track>createClientRegionFactory(ClientRegionShortcut.PROXY)
						.create("example-region");
		return region;
	}




}
