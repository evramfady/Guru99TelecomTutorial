package utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static JsonObject jsonConfig;

    private static final String PROP_PATH = "src/test/resources/config.properties";
    private static final String JSON_PATH = "src/test/resources/JsonConfig.json";

    private static void loadProperties() {
        if (properties == null) {
            try (FileInputStream inputStream = new FileInputStream(PROP_PATH)) {
                properties = new Properties();
                properties.load(inputStream);
            } catch (IOException e) {
                throw new RuntimeException("CRITICAL: Could not load properties file at " + PROP_PATH +
                        ". Check if the file exists in src/test/resources/");
            }
        }
    }

    private static void loadJson() {
        if (jsonConfig == null) {
            try (FileReader reader = new FileReader(JSON_PATH)) {
                jsonConfig = JsonParser.parseReader(reader).getAsJsonObject();
            } catch (IOException e) {
                throw new RuntimeException("CRITICAL: Could not load JSON config at " + JSON_PATH);
            }
        }
    }

    public static String getProperty(String key) {
        loadProperties();
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Key '" + key + "' not found in config.properties!");
        }
        return value;
    }

    public static String getJsonData(String key) {
        loadJson();
        if (!jsonConfig.has(key)) {
            throw new RuntimeException("Key '" + key + "' not found in JsonConfig.json!");
        }
        return jsonConfig.get(key).getAsString();
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getBaseUrl() {
        return getProperty("BASE_URL");
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicitWait"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicitWait"));
    }
}