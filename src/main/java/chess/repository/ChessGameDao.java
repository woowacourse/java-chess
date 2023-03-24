package chess.repository;

import chess.domain.ChessGame;
import chess.domain.TeamColor;
import chess.dto.ChessGameDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class ChessGameDao {

    private final Connection connection;

    public ChessGameDao() {
        connection = ConnectionProvider.getConnection();
    }

    public long save(final ChessGame game) {
        String queryStatement = "INSERT INTO game (turn, is_end) VALUES(?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryStatement,
                Statement.RETURN_GENERATED_KEYS); // queryAndReturnGeneratedKeys
            preparedStatement.setString(1, game.getTeamColor().name());
            preparedStatement.setBoolean(2, game.isEnd());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            long generatedGameId = 1L;
            if (keys.next()) {
                generatedGameId = keys.getLong(1);
            }
            return generatedGameId;
        } catch (SQLException e) {
            System.err.println("INSERT 오류 " + e.getMessage());
            throw new RuntimeException();
        }
    }

    public boolean updateStatus(final long gameId, final boolean isEnd) { // update
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

    public boolean updateTurn(final long gameId, final TeamColor color) { // update
        String queryStatement = "UPDATE game SET turn = ? WHERE game_id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryStatement);
            preparedStatement.setString(1, color.name());
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

    public Optional<ChessGameDto> findLastGame() { // queryOne
        String queryStatement = "SELECT * FROM game WHERE is_end IS FALSE ORDER BY game_id DESC LIMIT 1";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(new ChessGameDto(resultSet.getLong("game_id"), TeamColor.findByName(resultSet.getString("turn"))));
            }
            return Optional.empty();
        } catch (SQLException e) {
            System.err.println("SELECT 에러: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
