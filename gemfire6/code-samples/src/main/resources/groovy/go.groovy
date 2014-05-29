package groovy

import com.gemstone.gemfire.cache.CacheFactory
import com.gemstone.gemfire.cache.Region
import com.gemstone.gemfire.cache.partition.PartitionRegionHelper

/**
 * Created with IntelliJ IDEA.
 * User: jellin
 * Date: 5/28/14
 * Time: 11:56 AM
 * To change this template use File | Settings | File Templates.
 */

CacheFactory.getAnyInstance().getLogger().severe("Doing it in Groovy");
Region reg =  PartitionRegionHelper.getLocalPrimaryData(context.getDataSet());

reg.each {
    println "THe Key is ${it}";
}

return "hello";
