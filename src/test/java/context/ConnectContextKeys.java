package context;

import java.util.HashMap;

public class ConnectContextKeys {
    private static HashMap<ContextKeys, String> contextMap;

    public static void initializeContext(){
        contextMap= new HashMap<>();
    }

    public static void addContext(ContextKeys key, String value){
        if (contextMap==null)
            initializeContext();
        if (contextMap.get(key)!= null)
            contextMap.remove(key);
        contextMap.put(key,value);
    }

    public static String getContextValue(ContextKeys key){
        if (contextMap==null)
            return "Context is NULL";
        String value = contextMap.get(key);
        if (value == null)
            return "Context with key" +key+" not found";
        return value;
    }
}
