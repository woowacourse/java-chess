package chess.repository.dao;

import chess.domain.game.ChessGame;
import chess.dto.ChessGameDto;
import chess.repository.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.jetbrains.annotations.Nullable;

public class JdbcChessGameDao implements ChessGameDao {

    @Override
    public void save(final ChessGame chessGame) {
        final String sql = "INSERT INTO chess_game(turn) values(?)";

        final String turn = chessGame.getTurn().name();

        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, turn);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findLastInsertId() {
        final String sql = "SELECT MAX(id) FROM chess_game";

        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @Nullable ChessGameDto findById(final int id) {
        final String sql = "SELECT * FROM chess_game WHERE id = ?";

        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return ChessGameDto.of(resultSet.getInt("id"), resultSet.getString("turn"));
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(final ChessGame chessGame) {
        final String sql = "UPDATE chess_game SET turn = ? WHERE id = ?";

        final int id = chessGame.getId();
        final String turn = chessGame.getTurn().name();

        try (final Connection connection = ConnectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, turn);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
