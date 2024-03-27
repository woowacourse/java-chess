package chess.infra;

public class DatabaseConfiguration {

    private static final DatabaseConfiguration INSTANCE = new DatabaseConfiguration();
    private final String SERVER = "localhost:13306";
    private final String DATABASE = "chess";
    private final String OPTION = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    private final String MYSQL_CONNECT_URL_FORMAT = "jdbc:mysql://%s/%s";

    public static DatabaseConfiguration getInstance() {
        return INSTANCE;
    }

    public String getUrl() {
        return String.format(MYSQL_CONNECT_URL_FORMAT, SERVER, DATABASE + OPTION);
    }

    public String getUsername() {
        return USERNAME;
    }

    public String getPassword() {
        return PASSWORD;
    }
}
