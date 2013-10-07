import com.gemstone.gemfire.cache.client.ClientCacheFactory
import groovy.transform.ToString

def cache = new ClientCacheFactory()
        .set("name", "ClientWorker")
        .set("cache-xml-file", "clientCache.xml")
        .create()

def assets = cache.getRegion("Customers");

Customer c = new Customer(id:"1",name:"Jeff");

assets.put(c.id,c);

def out = assets.get("1")

println "****retrieved the object from the cache***"
println out
println "******************************************"

cache.close()

@ToString
class Customer implements Serializable{
    String id;
    String name;
}