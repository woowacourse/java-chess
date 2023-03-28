package chess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class DBChessStatusDao implements ChessStatusDao {
    private final DBConnection dbConnection = new DBConnection();

    @Override
    public List<String> readGameIds() {
        final var query = "SELECT game_id FROM chess_status;";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query);
             final ResultSet resultSet = preparedStatement.executeQuery()) {

            List<String> gameIds = new ArrayList<>();
            while (resultSet.next()) {
                final String gameId = resultSet.getString("game_id");
                gameIds.add(gameId);
            }

            return gameIds;
        } catch (final SQLException e) {
            throw new IllegalStateException("체스 게임 상태를 저장하는데 실패했습니다.");
        }
    }

    @Override
    public String readTurn(final String gameId) {
        final var query = "SELECT turn FROM chess_status WHERE game_id=?;";
        try (final Connection connection = dbConnection.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(gameId));

            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("turn");
            }

            throw new IllegalStateException("체스 게임의 순서에 대한 정보를 불러올 수 없습니다.");
        } catch (final SQLException e) {
            throw new IllegalStateException("체스 게임의 상태를 불러오는데 실패했습니다.");
        }
    }

    @Override
    public String create(final String turn) {
        final var query = "INSERT INTO chess_status (turn) VALUE (?);";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, turn);
            preparedStatement.executeUpdate();

            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getString(1);
            }

            throw new IllegalArgumentException("체스 게임의 상태를 생성할 수 없습니다.");
        } catch (final SQLException e) {
            throw new IllegalStateException("체스 게임 상태를 생성하는데 실패했습니다.");
        }
    }

    @Override
    public void update(final String turn, final String gameId) {
        final var query = "UPDATE chess_status SET turn=? WHERE game_id=?;";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, turn);
            preparedStatement.setString(2, gameId);

            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException("게임을 업데이트하는데 실패했습니다.");
        }
    }
}
;
