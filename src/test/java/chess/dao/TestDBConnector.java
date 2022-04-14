package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TestDBConnector {

    private static final String URL = "jdbc:h2:~/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public TestDBConnector() {
        createGame();
        createBoard();
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("데이터베이스 연결을 실패했습니다.");
    }

    private void createGame() {
        final String sql = "CREATE TABLE IF NOT EXISTS game (\n" +
                "  id int NOT NULL AUTO_INCREMENT,\n" +
                "  black_user_name varchar(15) NOT NULL,\n" +
                "  white_user_name varchar(15) NOT NULL,\n" +
                "  turn varchar(15) NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ");";

        try (Connection connection = getConnection()) {
            updateQuery(connection, sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createBoard() {
        final String sql = "CREATE TABLE IF NOT EXISTS board (\n" +
                "  id int NOT NULL AUTO_INCREMENT,\n" +
                "  game_id int NOT NULL,\n" +
                "  piece varchar(10) NOT NULL,\n" +
                "  position varchar(15) NOT NULL,\n" +
                "  color varchar(10) NOT NULL,\n" +
                "  PRIMARY KEY (id),\n" +
                "  CONSTRAINT game_id FOREIGN KEY (game_id) REFERENCES game (id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                ");";

        try (Connection connection = getConnection()) {
            updateQuery(connection, sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateQuery(Connection connection, String sql) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
