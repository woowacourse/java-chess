package chess.database.properties;

import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public final class ChessProperties {
    private static final String URL = "url";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    private final String url;
    private final String userName;
    private final String password;

    public ChessProperties() {
        final Map<String, String> properties = getProperties();
        this.url = properties.get(URL);
        this.userName = properties.get(USERNAME);
        this.password = properties.get(PASSWORD);
    }

    private Map<String, String> getProperties() {
        try (final FileReader fileReader = new FileReader("src/main/resources/application.yml")) {
            return new Yaml().load(fileReader);
        } catch (IOException e) {
            System.out.println("properties read Exception! = " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
