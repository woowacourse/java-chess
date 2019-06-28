package chess.dao;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;

public class DBManager {
    private static final String DB_HOST = "localhost";
    private static final String DB_NAME = "chess";
    private static final String DB_USER = "conatuseus";
    private static final String DB_PASSWORD = "1234";

    public static DataSource createDataSource() {
        MysqlDataSource ds = new MysqlDataSource();
        ds.setUrl("jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
        ds.setUser(DB_USER);
        ds.setPassword(DB_PASSWORD);
        ds.setDatabaseName(DB_NAME);
        return ds;
    }

}
