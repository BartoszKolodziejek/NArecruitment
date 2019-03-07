package pl.bartosz.kolodziejek.postReceiver.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private final static Configuration configuration = new Configuration();
    private Properties properties = new Properties();

    private Configuration() {
        try {
            System.out.println("I am reading configuration");
            properties.load(new FileInputStream("src/main/resources/configuration.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-2);
        }

    }

    public static Configuration getInstance(){
        return configuration;
    }

    public Object get(String key){
        return properties.get(key);
    }
}
