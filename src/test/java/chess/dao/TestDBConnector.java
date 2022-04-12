package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TestDBConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/test_chess";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public TestDBConnector() {
        createDatabase();
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

    private void createDatabase() {
        final String sql = "create database if not exists test_chess;";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306",
                "root", "1234")) {
            updateQuery(connection, sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createGame() {
        final String sql = "CREATE TABLE if not exists `game` (\n" +
                "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" +
                "  `black_user_name` varchar(15) NOT NULL,\n" +
                "  `white_user_name` varchar(15) NOT NULL,\n" +
                "  `turn` varchar(15) NOT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ");";

        try (Connection connection = getConnection()) {
            updateQuery(connection, sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createBoard() {
        final String sql = "CREATE TABLE if not exists `board` (\n" +
                "  `id` int(15) unsigned zerofill NOT NULL AUTO_INCREMENT,\n" +
                "  `game_id` int unsigned NOT NULL,\n" +
                "  `piece` varchar(10) NOT NULL,\n" +
                "  `position` varchar(15) NOT NULL,\n" +
                "  `color` varchar(10) NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  KEY `game_id` (`game_id`),\n" +
                "  CONSTRAINT `game_id` FOREIGN KEY (`game_id`) REFERENCES `game` (`id`) ON DELETE CASCADE ON UPDATE CASCADE\n" +
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
