package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String LOCAL_SERVER = "localhost:13306";
    private static final String SERVER_TIME = "?useSSL=false&serverTimezone=UTC";
    private static final String USER_NAME = "root";
    private static final String USER_PASSWORD = "root";
    private static final String COM_MYSQL_CJ_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_MYSQL = "jdbc:mysql://";
    public static final String DB_NAME = "db_name";

    public static Connection getConnection() {
        Connection con = null;

        // 드라이버 로딩
        try {
            Class.forName(COM_MYSQL_CJ_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            con = DriverManager.getConnection(JDBC_MYSQL + LOCAL_SERVER + "/" + DB_NAME + SERVER_TIME, USER_NAME, USER_PASSWORD);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("연결에 실패했습니다. 다시 시도해주세요.");
        }

        return con;
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
        }
    }
}
