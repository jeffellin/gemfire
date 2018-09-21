package gemfire;


import org.apache.geode.cache.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api")
public class CacheSizeController {


    @Autowired
    Region region;

    @GetMapping(path = "/count")
    public int getKeyCount() {
        return region.sizeOnServer();
    }


}
