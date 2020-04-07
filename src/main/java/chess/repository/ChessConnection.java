package chess.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;

public class ChessConnection {
    public static Connection getConnection(ConnectionProperties connectionProperties) {

        String server = connectionProperties.getServer();
        String database = connectionProperties.getDatabase();
        String option = connectionProperties.getOption();
        String userName = connectionProperties.getUserName();
        String password = connectionProperties.getPassword();

        try {
            Class.forName(connectionProperties.getJdbcDriver());
        } catch (ClassNotFoundException e) {
            throw new NoSuchElementException(String.format("%s 드라이버가 존재하지 않습니다.", connectionProperties.getJdbcDriver()));
        }

        try {
            return DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + option, userName, password);
        } catch (SQLException e) {
            throw new IllegalArgumentException("요청한 DB 연결을 수행할 수 없습니다.");
        }
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            System.err.println("con close 실패 :" + e.getMessage());
        }
    }

}
