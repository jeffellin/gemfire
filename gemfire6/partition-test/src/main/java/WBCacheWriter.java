import com.gemstone.gemfire.cache.*;
import com.gemstone.gemfire.cache.util.GatewayEvent;
import com.gemstone.gemfire.pdx.PdxInstance;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: jellin
 * Date: 3/9/14
 * Time: 5:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class WBCacheWriter implements CacheWriter,Declarable {
    @Override
    public void beforeUpdate(EntryEvent entryEvent) throws CacheWriterException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void beforeCreate(EntryEvent entryEvent) throws CacheWriterException {

        Region r = CacheFactory.getAnyInstance().getRegion("/PersistentEvent2");

        PdxInstance pdx = (PdxInstance) entryEvent.getNewValue();

        String id = (String) pdx.getField("id");

        CacheFactory.getAnyInstance().getCacheTransactionManager().begin();

        String txnId = CacheFactory.getAnyInstance().getCacheTransactionManager().getTransactionId().toString();
        r.put(txnId,pdx.getObject());

        CacheFactory.getAnyInstance().getCacheTransactionManager().commit();



    }

    @Override
    public void beforeDestroy(EntryEvent entryEvent) throws CacheWriterException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void beforeRegionDestroy(RegionEvent regionEvent) throws CacheWriterException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void beforeRegionClear(RegionEvent regionEvent) throws CacheWriterException {
        //To change body of implemented methods use File | Settings | File Templates.
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

