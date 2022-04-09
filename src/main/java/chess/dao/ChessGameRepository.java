package chess.dao;

import chess.domain.piece.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChessGameRepository implements ChessGameDao {

    @Override
    public Long save(final Color turn) {
        final String sql = "insert into chessGame (turn) values (?)";
        try (final Connection connection = JdbcConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, turn.getName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                return null;
            }
            return generatedKeys.getLong(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Color findTurnById(final Long id) {
        final String sql = "select turn from chessGame where id = ?";
        try (final Connection connection = JdbcConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return Color.from(resultSet.getString("turn"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void deleteById(final Long id) {
        final String sql = "delete from chessGame where id = ?";
        try (final Connection connection = JdbcConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTurnById(final Long id, final String turn) {
        final String sql = "update chessGame set turn = ? where id = ?";
        try (final Connection connection = JdbcConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, turn);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
