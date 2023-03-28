package chess.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static final String JDBC_MYSQL = "jdbc:mysql://";

    private final Configuration configuration = Configuration.getInstance();
    private final DatabaseName databaseName;

    public Database(final DatabaseName databaseName) {
        this.databaseName = databaseName;
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(getUrl(), configuration.getUsername(), configuration.getPassword());
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private String getUrl() {
        return JDBC_MYSQL + configuration.getServer() + "/" + databaseName.getName() + configuration.getOption();
    }
}
