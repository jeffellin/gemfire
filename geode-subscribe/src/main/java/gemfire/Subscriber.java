package gemfire;

import org.apache.geode.cache.*;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.pdx.PdxInstance;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;


public class Subscriber {

    static String REGIONNAME = "example-region";


    public static void main(String[] args) throws InterruptedException {
        ClientCache cache = new ClientCacheFactory().addPoolLocator("127.0.0.1", 10334)
                .set("log-level", "WARN").setPdxReadSerialized(true)
                .setPdxSerializer(new ReflectionBasedAutoSerializer("gemfire.Employee"))
                .setPoolSubscriptionEnabled(true).create();

        // create a region on the server
        CacheListener<Integer, PdxInstance> listener = new IntegerPdxInstanceCacheListener();

        Region<Integer, PdxInstance> region =
                cache.<Integer, PdxInstance>createClientRegionFactory(ClientRegionShortcut.CACHING_PROXY)
                        .addCacheListener(listener)
                        .create(REGIONNAME);

        region.registerInterestForAllKeys(InterestResultPolicy.KEYS);

        int inserted = region.keySetOnServer().size();
        System.out.println(String.format("Counted %d keys in region %s", inserted, region.getName()));


        while (true) {
            Thread.sleep(1000);
        }

    }

    private static class IntegerPdxInstanceCacheListener implements CacheListener<Integer, PdxInstance> {
        public void afterCreate(EntryEvent<Integer, PdxInstance> entryEvent) {
            System.out.println("afterCreate");
            System.out.println(entryEvent.getKey());
            System.out.println(entryEvent.getSerializedNewValue().getDeserializedValue().getField("firstName"));
        }

        public void afterUpdate(EntryEvent<Integer, PdxInstance> entryEvent) {
            System.out.println("afterUpdate");
            System.out.println(entryEvent.getKey());
            System.out.println(entryEvent.getSerializedNewValue().getDeserializedValue().getField("firstName"));


        }

        public void afterInvalidate(EntryEvent<Integer, PdxInstance> entryEvent) {

        }

        public void afterDestroy(EntryEvent<Integer, PdxInstance> entryEvent) {

        }

        public void afterRegionInvalidate(RegionEvent<Integer, PdxInstance> regionEvent) {

        }

        public void afterRegionDestroy(RegionEvent<Integer, PdxInstance> regionEvent) {

        }

        public void afterRegionClear(RegionEvent<Integer, PdxInstance> regionEvent) {

        }

        public void afterRegionCreate(RegionEvent<Integer, PdxInstance> regionEvent) {

        }

        public void afterRegionLive(RegionEvent<Integer, PdxInstance> regionEvent) {

        }
    }
}


