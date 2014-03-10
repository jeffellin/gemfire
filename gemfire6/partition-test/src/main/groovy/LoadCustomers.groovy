import com.ellin.Customer
import com.gemstone.gemfire.cache.client.ClientCacheFactory

def cache = new ClientCacheFactory()
        .set("name", "ClientWorker")
        .set("cache-xml-file", "clientCache.xml")
        .create()

def assets = cache.getRegion("Customers");

for (i in 1..1000){
    Customer c = new Customer(id:UUID.randomUUID().toString(),customerName:UUID.randomUUID().toString());
    assets.put(c.id,c);
}

println "****retrieved the object from the cache***"
println assets.size()
println "******************************************"

cache.close()

