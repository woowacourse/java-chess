package chess.dao;

import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class ChessDao {

    private static final HikariDataSource DATA_SOURCE = initializeDataSource();

    private static HikariDataSource initializeDataSource() {
        String server = "localhost:13306";
        String database = "chess";
        String option = "?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8";
        String userName = "root";
        String password = "root";

        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl("jdbc:mysql://" + server + "/" + database + option);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    protected static Connection connection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }
}
