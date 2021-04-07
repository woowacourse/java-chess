package chess.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnectionSetting implements ConnectionSetting {

    private static final String server = "localhost:13306";
    private static final String database = "chessGame";
    private static final String option = "?useSSL=false&serverTimezone=UTC";
    private static final String userName = "root";
    private static final String password = "root";

    private static final MySQLConnectionSetting INSTANCE = new MySQLConnectionSetting();

    private MySQLConnectionSetting() {
    }

    public static MySQLConnectionSetting getInstance() {
        return INSTANCE;
    }

    @Override
    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager
                .getConnection("jdbc:mysql://" + server + "/" + database + option, userName,
                    password);
        } catch (ClassNotFoundException exception) {
            System.err.println("JDBC Driver load 오류: " + exception.getMessage());
            exception.printStackTrace();
        } catch (SQLException exception) {
            System.err.println("연결 오류: " + exception.getMessage());
            exception.printStackTrace();
        }
        return null;
    }
}
