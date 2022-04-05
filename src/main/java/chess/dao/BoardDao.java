package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    public void save(Position position, Piece piece) {
        String sql = "insert into board (position, color, piece) values (?,?,?)";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position.getStringValue());
            statement.setString(2, piece.getColor());
            statement.setString(3, piece.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateByPosition(Position nextPosition, Position previousPosition) {
        if (findByPosition(nextPosition) == null) {
            save(nextPosition, findByPosition(previousPosition));
            return;
        }
        String sql = "update board set color = ?, piece = ? where position = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            Piece piece = findByPosition(previousPosition);
            statement.setString(1, piece.getColor());
            statement.setString(2, piece.getType());
            statement.setString(3, nextPosition.getStringValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Piece findByPosition(Position position) {
        final String sql = "select * from board where position = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position.getStringValue());
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return PieceFactory.create(
                    resultSet.getString("color"),
                    resultSet.getString("piece")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Board findAll() {
        final String sql = "select * from board";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();

            Map<Position, Piece> value = new HashMap<>();
            while (resultSet.next()) {
                Position position = Position.valueOf(resultSet.getString("position"));
                Piece piece = PieceFactory.create(
                        resultSet.getString("color"),
                        resultSet.getString("piece"));
                value.put(position, piece);
            }
            return new Board(value);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void delete(Position position) {
        String sql = "delete from board where position = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position.getStringValue());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        String sql = "delete from board where 1";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
