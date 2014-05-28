package functions.partitioned;

import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.Region;
import com.gemstone.gemfire.cache.execute.Function;
import com.gemstone.gemfire.cache.execute.FunctionContext;
import com.gemstone.gemfire.cache.execute.RegionFunctionContext;
import com.gemstone.gemfire.cache.partition.PartitionRegionHelper;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: jellin
 * Date: 5/28/14
 * Time: 9:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataAwareFunction implements Declarable, Function {

    /**
     * Since this is a Declarable, we can pass configuration parameters from the cache.xml
     * @param properties
     */
    @Override
    public void init(Properties properties) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean hasResult() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Depending on how this function is called the dataset available may be different.  This is only applicable to
     * if the function is run onRegion() to a partitionedRegion
     *
     * @param functionContext
     */
    @Override
    public void execute(FunctionContext functionContext) {

        RegionFunctionContext rfctx = (RegionFunctionContext)functionContext;
        Region<Object,Object> localData;

        //this will return all data on the node, including primary and backup data
        localData = PartitionRegionHelper.getLocalData(rfctx.getDataSet());

        //this will return all data on the node from the primary bucket
        //in most cases this is what I would want
        localData = PartitionRegionHelper.getLocalPrimaryData(rfctx.getDataSet());

        //this will return all data as filtered by the functionContext. if no filter is passed and WriteOptimized = true
        //this should return the same data set at getLocalPrimaryData. if a filter is added it will return only data
        //within the same bucket as the passed keys.  See .withFilter() on the Execution object returned by the
        //Function Service, if no filter and  writeOptimized = false, it will return data from primary and redunant
        //buckets, with filter and writeOptimezed = false it will return records that are containted in the
        //same buckets as the filter and may contain data from the primary or redundant bucket.

        localData = PartitionRegionHelper.getLocalDataForContext(rfctx);


    }

    /**
     * a unique Name for the function so that it can be executed by the client
     * @return
     */
    @Override
    public String getId() {
        return "DataAwareFunction";  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * If set to true, the data will run on all nodes,  if a .withFilter() is used when calling the function the
     * function will execute based on the partition where the primary bucket for the keys passed in is located. If false
     * the function will execute on the least number of nodes possible.  This may mean that the functionContext may
     * receive data from the redundant and not primary buckets.
     *
     * @return true/false
     */
    @Override
    public boolean optimizeForWrite() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isHA() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
