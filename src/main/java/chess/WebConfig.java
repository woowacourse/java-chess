package chess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static spark.Spark.port;
import static spark.Spark.staticFiles;

public class WebConfig {
    private static final String STATIC_FILE_PATH = "/public";

    private static final String ADDRESS = "localhost:13306";
    private static final Integer PORT = 4567;

    private static final String DB_NAME = "web_chess";
    private static final String DB_OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER_NAME = "root";
    private static final String DB_USER_PASS_WORD = "root";

    public WebConfig() {
        resourceMapping();
        port(PORT);
    }

    private void resourceMapping() {
        staticFiles.location(STATIC_FILE_PATH);
    }

    public Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection("jdbc:mysql://" + ADDRESS + "/" + DB_NAME + DB_OPTION, DB_USER_NAME, DB_USER_PASS_WORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
