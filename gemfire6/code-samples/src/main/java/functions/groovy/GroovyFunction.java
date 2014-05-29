package functions.groovy;

import com.gemstone.gemfire.cache.Declarable;
import com.gemstone.gemfire.cache.execute.Function;
import com.gemstone.gemfire.cache.execute.FunctionContext;
import com.gemstone.gemfire.cache.execute.RegionFunctionContext;
import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: jellin
 * Date: 5/28/14
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class GroovyFunction implements Function, Declarable {


    @Override
    public void init(Properties properties) {

    }

    @Override
    public boolean hasResult() {
        return false;
    }

    @Override
    public void execute(FunctionContext functionContext) {


        RegionFunctionContext regionFunctionContext = (RegionFunctionContext)functionContext;

        String[] roots = new String[] {"?Users/jellin/foo"};
        try{
            String script = (String)functionContext.getArguments();
            GroovyScriptEngine gse = new GroovyScriptEngine(roots);
            Binding binding =  new Binding();
            binding.setVariable("context",regionFunctionContext);
            Object result = gse.run(script,binding);
            regionFunctionContext.getResultSender().lastResult(result);

        }  catch(Exception e){

            e.printStackTrace();

        }



    }

    @Override
    public String getId() {
        return "GroovyFunction";  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean optimizeForWrite() {
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isHA() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
