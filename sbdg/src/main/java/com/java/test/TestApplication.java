package com.java.test;

import com.java.test.model.Customer;
import org.apache.geode.cache.Region;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.client.ClientRegionFactoryBean;
import org.springframework.data.gemfire.config.annotation.*;



@SpringBootApplication
@EnableEntityDefinedRegions( basePackageClasses = Customer.class)
public class TestApplication {




    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(TestApplication.class, args);


    }

    @Bean
    RegionConfigurer cacheServerPortConfigurer( ){

        return new RegionConfigurer(){
            public void configure(String beanName, ClientRegionFactoryBean<?, ?> bean){
                    System.out.println(beanName+"---------");
            };
        };
    }

    @Bean
    ApplicationRunner runner(Region customer) {

        return args -> {



                customer.put("hi","world");
                System.out.println(customer.get("hi"));




        };
    }

}


