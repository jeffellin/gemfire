package com.example.gfsh;

import com.example.gfsh.model.InsideObject;
import com.example.gfsh.model.OutsideObject;
import org.apache.geode.cache.Cache;
import org.apache.geode.cache.CacheFactory;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.RegionShortcut;
import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.apache.geode.distributed.ServerLauncher;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.data.gemfire.tests.integration.ClientServerIntegrationTestsSupport;
import org.springframework.data.gemfire.tests.integration.ForkingClientServerIntegrationTestsSupport;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestGeode extends ForkingClientServerIntegrationTestsSupport {

    private static ServerLauncher serverLauncher;


    @BeforeClass
    public static void startGemFireServer() throws IOException {
        startGemFireServer(GemFireServerConfiguration.class);

        int port = Integer.parseInt(System.getProperty(ClientServerIntegrationTestsSupport.GEMFIRE_CACHE_SERVER_PORT_PROPERTY,"40404"));

        ClientCache cache = new ClientCacheFactory().addPoolServer("localhost",port)
                .setPdxSerializer(new ReflectionBasedAutoSerializer("com.example.gfsh.model.*"))
                .create();

                cache.createClientRegionFactory(ClientRegionShortcut.PROXY).create("foo");
    }

    @AfterClass
    public static void shutdown() throws IOException {

        ClientCache cache = ClientCacheFactory.getAnyInstance();
        cache.close(false);
        stopGemFireServer();
    }
    @Test
    public void testIt() {



        Region region = ClientCacheFactory.getAnyInstance().getRegion("foo");

        OutsideObject obj1 = new OutsideObject();
        obj1.setName("name");
        obj1.setInsideObject(new InsideObject("lllll"));

        region.put("foo", obj1);
        OutsideObject obj2 = (OutsideObject) region.get("foo");


        OutsideObject obj3 = new OutsideObject();
        obj3.setName("name");
        obj3.setInsideObject(new InsideObject("lllll"));

        assertEquals(obj3,obj2);


    }


    public static class GemFireServerConfiguration {


        public static void main(String[] args) {


            int port = Integer.parseInt(System.getProperty(ClientServerIntegrationTestsSupport.GEMFIRE_CACHE_SERVER_PORT_PROPERTY,"40404"));

            serverLauncher= new ServerLauncher.Builder()
                    .setServerPort(port)
                    .setMemberName("server1")
                    .setPdxSerializer(new ReflectionBasedAutoSerializer("com.example.gfsh.model."))
                    .build();


            serverLauncher.start();


            serverLauncher.waitOnServer();

            Cache cache = CacheFactory.getAnyInstance();
            cache.createRegionFactory(RegionShortcut.REPLICATE).create("foo");

        }


    }
}

