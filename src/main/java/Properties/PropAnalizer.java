package Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropAnalizer {
    Properties prop;
    String fileName;
    public String appName;
    public String appVersion;
    public String apiToken;

    public PropAnalizer(){
        prop = new Properties();
        fileName = "app.config";
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        appName = prop.getProperty("app.name");
        appVersion = prop.getProperty("app.version");
        apiToken = prop.getProperty("api.token");

    }
}
