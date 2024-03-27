package chess.infra;

public class DatabaseConfiguration {

    private final String MYSQL_CONNECT_URL_FORMAT = "jdbc:mysql://%s:%s/%s";

    private static final DatabaseConfiguration INSTANCE = new DatabaseConfiguration();
    private final String host = "localhost";
    private final String post = "13306";
    private final String database = "chess";
    private final String option = "?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private final String username = "root";
    private final String password = "root";

    private DatabaseConfiguration() {
    }

    public static DatabaseConfiguration getInstance() {
        return INSTANCE;
    }

    public String getUrl() {
        return String.format(MYSQL_CONNECT_URL_FORMAT, host, post, database + option);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
