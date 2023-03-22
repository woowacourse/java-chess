package chess.infrastructure.persistence.dao;

import chess.infrastructure.persistence.entity.ChessGameEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static chess.infrastructure.persistence.JdbcConnectionUtil.connection;

public class ChessGameDao {

    public void save(final ChessGameEntity chessGameEntity) {
        final String sql = "INSERT INTO chess_game(id, state, turn, winner) VALUES (?, ?, ?, ?)";
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, chessGameEntity.id().toString());
            preparedStatement.setString(2, chessGameEntity.state());
            preparedStatement.setString(3, chessGameEntity.turn());
            preparedStatement.setString(4, chessGameEntity.winner());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<ChessGameEntity> findById(final Long id) {
        final String query = "SELECT * FROM chess_game where id = ?";
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id.toString());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(new ChessGameEntity(
                    resultSet.getObject(1, Long.class),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(final ChessGameEntity chessGameEntity) {
        final String sql = "UPDATE chess_game SET state = ?,turn = ?, winner = ? ";
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, chessGameEntity.state());
            preparedStatement.setString(2, chessGameEntity.turn());
            preparedStatement.setString(3, chessGameEntity.winner());
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAll() {
        final String sql = "DELETE FROM chess_game";
        try (final Connection connection = connection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
