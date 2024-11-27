package framework;

import static framework.PropertyLoader.retrieveProperty;

public class Config {

    public static Boolean logsEnabled = Boolean.parseBoolean(PropertyLoader.getProperty("logsEnabled"));
    public static String restApiBaseUrl =  PropertyLoader.getProperty("restApiBaseUrl");
}
