package chess.repository;

import chess.domain.ChessGame;
import chess.domain.TeamColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChessGameDao {

    private final Connection connection;

    public ChessGameDao() {
        connection = ConnectionProvider.getConnection();
    }

    public void save(final ChessGame game) {
        String queryStatement = "INSERT INTO game (turn, is_end) VALUES(?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryStatement);
            preparedStatement.setString(1, convertTeamColorToString(game.getTeamColor()));
            preparedStatement.setBoolean(2, !game.isPlaying());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("INSERT 오류 " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean updateStatus(final long gameId, final boolean isEnd) {
        String queryStatement = "UPDATE game SET is_end = ? WHERE game_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryStatement);
            preparedStatement.setBoolean(1, isEnd);
            preparedStatement.setLong(2, gameId);
            int result = preparedStatement.executeUpdate();

            if (result != 1) {
                return true;
            }

        } catch (SQLException e) {
            System.err.println("UPDATE 오류: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    private String convertTeamColorToString(final TeamColor color) {
        return color.name();
    }

}
