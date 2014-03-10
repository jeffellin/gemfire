import com.gemstone.gemfire.cache.CacheFactory;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.util.GatewayEvent;
import com.gemstone.gemfire.cache.util.GatewayEventListener;
import com.gemstone.gemfire.cache.Declarable;




import java.util.List;
import java.util.Properties;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: jellin
 * Date: 3/9/14
 * Time: 11:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class GatewayListener implements Declarable, GatewayEventListener {


    @Override
    public boolean processEvents(List<GatewayEvent> gatewayEvents) {

        String batch = UUID.randomUUID().toString();
        CacheFactory.getAnyInstance().getLogger().info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ Logging Event");
        Region r = CacheFactory.getAnyInstance().getRegion("/BUPE");

        CacheFactory.getAnyInstance().getCacheTransactionManager().begin();
        for(GatewayEvent event : gatewayEvents){
            r.put(new PartitionResolverKey(event.getKey().toString(),batch),event.getDeserializedValue());
        }
        CacheFactory.getAnyInstance().getCacheTransactionManager().commit();


        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void close() {



        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void init(Properties properties) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
