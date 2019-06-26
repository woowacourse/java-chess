package chess.utils;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DBUtil {
    private static final String server = "localhost";
    private static final String userName = "chess";
    private static final String password = "1234";
    private static final String portNumber = ":3306";

    public static DataSource getDataSource(final String database) {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUser(userName);
        ds.setPassword(password);
        ds.setDatabaseName(database);
        ds.setUrl("jdbc:mysql://" + server + portNumber + "/" +
                database + "?useSSL=false&serverTimezone=UTC");

        return ds;
    }
}
