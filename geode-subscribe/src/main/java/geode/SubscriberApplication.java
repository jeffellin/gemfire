package gemfire;

import org.apache.geode.cache.*;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.pdx.PdxInstance;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SubscriberApplication {

    static String REGIONNAME = "example-region";


    public static void main(String[] args) {
        SpringApplication.run(SubscriberApplication.class, args);
    }

    @Bean
    public ClientCache cache()
    {
        ClientCache cache = new ClientCacheFactory().addPoolLocator("127.0.0.1", 10334)
                .set("log-level", "WARN").setPdxReadSerialized(true)
                .setPdxSerializer(new ReflectionBasedAutoSerializer("com.example.gemfirehack.model.Track"))
                .setPoolSubscriptionEnabled(true).create();

        return cache;
    }


    @Bean
    public Region trackRegion(ClientCache cache){

        CacheListener<Long, PdxInstance> listener = new TrackCacheListener();

        Region<Long, PdxInstance> region =
                cache.<Long, PdxInstance>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
                        .addCacheListener(listener)
                        .create(REGIONNAME);

        region.registerInterestForAllKeys(InterestResultPolicy.KEYS);

        return region;
    }




}


