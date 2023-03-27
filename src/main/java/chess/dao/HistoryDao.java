package chess.dao;

import chess.dto.MoveDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryDao implements ChessDao {
    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveHistory(final MoveDto moveDto, final int gameRoomId) {
        final String historySaveQuery = "INSERT INTO history(source, target, game_id) VALUES (?,?,?)";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(historySaveQuery)) {
            preparedStatement.setString(1, moveDto.getSource());
            preparedStatement.setString(2, moveDto.getTarget());
            preparedStatement.setInt(3, gameRoomId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<MoveDto> selectAllHistory(int notFinishedGameId) {
        String selectAllQuery = "SELECT source, target FROM history WHERE game_id = ?";
        List<MoveDto> moveDtos = new ArrayList<>();

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(selectAllQuery)) {
            preparedStatement.setInt(1, notFinishedGameId);
            ResultSet historyResult = preparedStatement.executeQuery();
            while (historyResult.next()) {
                moveDtos.add(makeHistory(historyResult));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return moveDtos;
    }

    private MoveDto makeHistory(final ResultSet historyResultSet) throws SQLException {
        String source = historyResultSet.getString("source");
        String target = historyResultSet.getString("target");
        return new MoveDto(source, target);
    }

    @Override
    public void saveInitialGame() {
        final String gameSaveQuery = "INSERT INTO game(finished) value (false)";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(gameSaveQuery)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findNotFinishedGameId() {
        final String notFinishedGameQuery = "SELECT * FROM game WHERE finished=0";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(notFinishedGameQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
            throw new IllegalStateException();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existCurrentGame() {
        final String notFinishedGameQuery = "SELECT * FROM game WHERE finished=0";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(notFinishedGameQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setGameFinished(final int gameId) {
        final String gameSetFinishedQuery = "UPDATE game SET finished = true WHERE id = ?";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(gameSetFinishedQuery)) {
            preparedStatement.setInt(1, gameId);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

