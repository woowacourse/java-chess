package chess.dao;

public class ConnectionProperties {
    private String server = "localhost:3306";
    private String database = "woowa-chess";
    private String option = "?useSSL=false&serverTimezone=UTC";
    private String userName = "root";
    private String password = "root";
    private String jdbcDriver = "org.mariadb.jdbc.Driver";

    public ConnectionProperties() {
    }

    public ConnectionProperties(String server, String database, String option, String userName, String password, String jdbcDriver) {
        this.server = server;
        this.database = database;
        this.option = option;
        this.userName = userName;
        this.password = password;
        this.jdbcDriver = jdbcDriver;
    }

    public String getServer() {
        return server;
    }

    public String getDatabase() {
        return database;
    }

    public String getOption() {
        return option;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getJdbcDriver() {
        return jdbcDriver;
    }
}
