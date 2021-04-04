package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public final class ConnectDB {

    public static Connection getConnection() throws Exception {
        String server = "localhost:13306";
        String database = "chess";
        String option = "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
        String userName = "root";
        String password = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");

        return DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
    }
}
