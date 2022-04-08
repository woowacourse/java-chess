package chess.dao;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.utils.JdbcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BoardDao {

    public void save(Position position, Piece piece, int gameNumber) {
        String sql = "insert into board (game_number, position, color, piece) values (?,?,?,?)";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gameNumber);
            statement.setString(2, position.getStringValue());
            statement.setString(3, piece.getColor());
            statement.setString(4, piece.getType());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateByPosition(Position nextPosition, Position previousPosition, int gameNumber) {
        if (findByPositionAndGameNumber(nextPosition, gameNumber) == null) {
            save(nextPosition, findByPositionAndGameNumber(previousPosition, gameNumber), gameNumber);
            return;
        }
        String sql = "update board set color = ?, piece = ? where position = ? and game_number = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            Piece piece = findByPositionAndGameNumber(previousPosition, gameNumber);
            statement.setString(1, piece.getColor());
            statement.setString(2, piece.getType());
            statement.setString(3, nextPosition.getStringValue());
            statement.setInt(4, gameNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Piece findByPositionAndGameNumber(Position position, int gameNumber) {
        final String sql = "select * from board where position = ? and game_number = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position.getStringValue());
            statement.setInt(2, gameNumber);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return PieceType.createPiece(
                    resultSet.getString("color"),
                    resultSet.getString("piece")
            );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Board findAllByGameNumber(int gameNumber) {
        final String sql = "select * from board where game_number = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gameNumber);
            ResultSet resultSet = statement.executeQuery();

            Map<Position, Piece> value = new HashMap<>();
            while (resultSet.next()) {
                Position position = Position.valueOf(resultSet.getString("position"));
                Piece piece = PieceType.createPiece(
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

    public void delete(Position position, int gameNumber) {
        String sql = "delete from board where position = ? and game_number = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, position.getStringValue());
            statement.setInt(2, gameNumber);
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

    public void deleteAllByGameNumber(int gameNumber) {
        String sql = "delete from board where game_number = ?";
        try (Connection connection = JdbcConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, gameNumber);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
