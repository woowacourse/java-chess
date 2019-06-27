package chess.persistence;

import org.sqlite.SQLiteConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLiteDataSource implements AbstractDataSource {
    private String dbFileName = "test.db";

    @Override
    public Connection getConnection() {
        Connection con = null;
        String server = "localhost"; // MySQL 서버 주소
        String database = "lotto_db"; // MySQL DATABASE 이름
        String userName = "user"; //  MySQL 서버 아이디
        String password = "gmlgus12"; // MySQL 서버 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! JDBC Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 드라이버 연결
        try {
            SQLiteConfig config = new SQLiteConfig();
            con = DriverManager.getConnection("jdbc:sqlite:" + this.dbFileName, config.toProperties());
            createRoomTable(con);
            createBoardTable(con);
            createTurnTable(con);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("연결 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }

    private void createBoardTable(Connection connection) throws SQLException {
        String table = "CREATE TABLE IF NOT EXISTS board_state (" +
                "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "  room_id int(11) NOT NULL," +
                "  loc_x varchar(16) NOT NULL," +
                "  loc_y varchar(16) NOT NULL," +
                "  type varchar(64) NOT NULL," +
                "  FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE" +
                ")";

        PreparedStatement statement = connection.prepareStatement(table);
        statement.executeUpdate();
    }

    private void createRoomTable(Connection connection) throws SQLException {
        String table = "CREATE TABLE IF NOT EXISTS room (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title varchar(255) NOT NULL" +
                ")";

        PreparedStatement statement = connection.prepareStatement(table);
        statement.executeUpdate();
    }

    private void createTurnTable(Connection connection) throws SQLException {
        String table = "CREATE TABLE IF NOT EXISTS turn (" +
                "room_id int PRIMARY KEY," +
                "current varchar(45) NOT NULL" +
                ")";

        PreparedStatement statement = connection.prepareStatement(table);
        statement.executeUpdate();
    }

}
