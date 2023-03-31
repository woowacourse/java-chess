package chess.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChessTest {

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
