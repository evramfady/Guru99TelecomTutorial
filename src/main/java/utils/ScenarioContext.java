package utils;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {
    private static final ThreadLocal<Map<String, Object>> scenarioData =
            ThreadLocal.withInitial(HashMap::new);

    private static ThreadLocal<String> customerId = new ThreadLocal<>();

    public static void setCustomerId(String id)
    {
        customerId.set(id);
    }
    public static String getCustomerId()
    {
        return customerId.get();
    }

    public static void setContext(String key, Object value) {
        scenarioData.get().put(key, value);
    }

    public static Object getContext(String key) {
        return scenarioData.get().get(key);
    }
}