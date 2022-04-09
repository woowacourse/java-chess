package chess.dao;

import chess.dao.util.DatabaseConnector;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Pieces;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private static final String NON_PIECE_EXCEPTION_MESSAGE = "해당위치에는 말이 없습니다.";

    DatabaseConnector databaseConnector = new DatabaseConnector();

    public void save(Piece piece, String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "insert into piece (name, color, position, game_id) values (?, ?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, piece.getName());
            statement.setString(2, piece.getColor().getName());
            statement.setString(3, piece.getPosition().getPosition());
            statement.setString(4, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveAll(List<Piece> pieces, String gameId) {
        for (Piece piece : pieces) {
            save(piece, gameId);
        }
    }


    public void deleteByPosition(String position, String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "delete from piece where position = ? and game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setString(2, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateByPosition(String source, String target, String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "update piece set position = ? where position = ? and game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, target);
            statement.setString(2, source);
            statement.setString(3, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Pieces findAll(String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "select name, color, position from piece where game_id = ?";
        final List<Piece> members = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                members.add(PieceFactory.of(resultSet.getString("name"), resultSet.getString("color"),
                    resultSet.getString("position")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Pieces(members);
    }

    public Piece findByPosition(String position, String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "select name, color, position from piece where position = ? and game_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.setString(2, gameId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException(NON_PIECE_EXCEPTION_MESSAGE);
            }
            return PieceFactory.of(resultSet.getString("name"),
                resultSet.getString("color"),
                resultSet.getString("position"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAll(String gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "delete from piece where game_id = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, gameId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
