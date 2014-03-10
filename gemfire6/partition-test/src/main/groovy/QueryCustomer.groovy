import com.ellin.Customer
import com.gemstone.gemfire.cache.client.ClientCacheFactory
import com.gemstone.gemfire.cache.query.Query
import com.gemstone.gemfire.cache.query.QueryService
import com.gemstone.gemfire.cache.query.SelectResults

def cache = new ClientCacheFactory()
        .set("name", "ClientWorker")
        .set("cache-xml-file", "clientCache.xml")
        .create()

def assets = cache.getRegion("Customers");

Customer c = new Customer(id:"1",customerName:"Jeff",customerLocation: "US");
Customer c2 = new Customer(id:"2",customerName:"Bob",customerLocation: "US");
Customer c3 = new Customer(id:"3",customerName:"Bob",customerLocation: "EU");


assets.put(c.id,c);
assets.put(c2.id,c2);
assets.put(c3.id,c3);



System.err.println("************************************Query Customer by Name*************************************");

String queryString = "IMPORT com.ellin.Customer; " +
        "SELECT  * FROM /Customers c where c.getCustomerName() = 'Bob'";

SelectResults<Customer> results = (SelectResults <Customer>)doQuery(queryString,cache);

results.each {
    println it;
}

System.err.println("************************************Query Customer by Location*************************************");

String queryString2 = "IMPORT com.ellin.Customer; " +
        "SELECT  * FROM /Customers c where c.getCustomerLocation = 'US'";

(SelectResults <Customer>)doQuery(queryString2,cache).each {
    println it;
};




cache.close()


public SelectResults<?> doQuery ( queryString, cache)
{


    QueryService qs = cache.getQueryService();

    Query q = qs.newQuery(queryString);

    SelectResults<?> results = null;
    try
    {
        results = (SelectResults<?>)q.execute();
    }

    catch (Exception e)
    {
        e.printStackTrace();

    }
    return results;
}