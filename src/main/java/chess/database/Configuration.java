package chess.database;

public class Configuration {

    private static final Configuration INSTANCE = new Configuration();

    private static final String SERVER = "localhost:13306";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    private Configuration() {
    }

    public static Configuration getInstance() {
        return INSTANCE;
    }

    public String getServer() {
        return SERVER;
    }

    public String getOption() {
        return OPTION;
    }

    public String getUsername() {
        return USERNAME;
    }

    public String getPassword() {
        return PASSWORD;
    }
}
