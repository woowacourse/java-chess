package chess.domain.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChessTest {

    public static void clearAll(Connection connection) {
        try {
            setOffForeignKeyCheck(connection);
            clearBoard(connection);
            clearGame(connection);
            clearUser(connection);
            setOnForeignKeyCheck(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new IllegalStateException("데이터베이스를 초기화하는데 실패했습니다.");
        }
    }

    private static void setOffForeignKeyCheck(Connection connection) {
        final String query = "SET FOREIGN_KEY_CHECKS = 0";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("외래키 제약 조건 해제를 실패했습니다.");
        }
    }

    private static void setOnForeignKeyCheck(Connection connection) {
        final String query = "SET FOREIGN_KEY_CHECKS = 1";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("외래키 제약 조건 설정을 실패했습니다.");
        }
    }

    private static void clearBoard(Connection connection) throws SQLException {
        final String query = "TRUNCATE Board";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }

    private static void clearGame(Connection connection) throws SQLException {
        final String query = "TRUNCATE Game";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }

    private static void clearUser(Connection connection) throws SQLException {
        final String query = "TRUNCATE User";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
    }

    public static void createMockUser(Connection connection, String userId, String nickname) {
        try {
            final String query = "INSERT INTO User (user_id, nickname) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, nickname);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("가짜 유저를 생성하는데 실패하였습니다.");
        }
    }

    public static void createMockGame(Connection connection, String gameId, String userId) {
        try {
            final String query = "INSERT INTO Game (game_id, user_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gameId);
            preparedStatement.setString(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("가짜 게임을 생성하는데 실패하였습니다.");
        }
    }
}
