package chess.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private final DataSource dataSource;

    public DbConnector(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() throws SQLException {

        try {
            Class.forName(dataSource.getDriverClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(dataSource.getUrl(), dataSource.getProperties());
    }
}
