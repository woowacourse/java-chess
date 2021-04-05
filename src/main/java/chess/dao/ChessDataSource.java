package chess.dao;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ChessDataSource {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";

    private final HikariDataSource dataSource;

    public ChessDataSource() {
        this.dataSource = initializeDataSource();
    }

    private static HikariDataSource initializeDataSource() {
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION);
        dataSource.setUsername(USER_NAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    public Connection connection() throws SQLException {
        return dataSource.getConnection();
    }
}
