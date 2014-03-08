import com.ellin.Customer
import com.gemstone.gemfire.cache.client.ClientCacheFactory

def cache = new ClientCacheFactory()
        .set("name", "ClientWorker")
        .set("cache-xml-file", "clientCache.xml")
        .create()

def assets = cache.getRegion("Customers");

Customer c = new Customer(id:"1",customerName:"Jeff");


assets.put(c.id,c);

def out = assets.get("1")

println "****retrieved the object from the cache***"
println out
println "******************************************"

cache.close()

