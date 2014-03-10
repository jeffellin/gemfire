import com.gemstone.gemfire.cache.EntryOperation;
import com.gemstone.gemfire.cache.PartitionResolver;
import com.sun.org.apache.xml.internal.serializer.utils.SerializerMessages_zh_CN;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: jellin
 * Date: 3/8/14
 * Time: 4:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class PartitionResolverKey implements PartitionResolver, Serializable

{


    private String txnId;

    private String batch;

    public PartitionResolverKey(String txnId, String batch){
        this.txnId = txnId;
        this.batch = batch;
    }

    @Override
    public Object getRoutingObject(EntryOperation entryOperation) {
        return batch;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public String getName() {
        return "MyPartitionResolover";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void close() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
