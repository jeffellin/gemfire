package com.example.gfsh.config;

import com.example.gfsh.model.InsideObject;
import com.example.gfsh.model.OutsideObject;
import org.apache.geode.cache.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeodeConfig {

    @Autowired
    Region region;


    public void doSomething(){

        System.out.println("hlelelelelel");
        OutsideObject obj1 = new OutsideObject();
        obj1.setName("name");
        obj1.setInsideObject(new InsideObject("lllll"));
        System.err.println("---"+obj1);

        region.put("foo", obj1);
        OutsideObject obj2 = (OutsideObject) region.get("foo");
        System.err.println("---"+ obj2);


        OutsideObject obj3 = new OutsideObject();
        obj3.setName("name");
        obj3.setInsideObject(new InsideObject("lllll"));

        System.err.println("---"+obj3.equals(obj2));


    }
}
