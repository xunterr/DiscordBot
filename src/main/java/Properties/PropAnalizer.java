package Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropAnalizer {
    Properties prop;
    String fileName;


    public PropAnalizer(){
        prop = new Properties();
        fileName = "./src/main/resources/app.properties";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public String get(String name){
        return prop.getProperty(name);
    }
}
