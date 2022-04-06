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

    public void save(Piece piece) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "insert into piece (name, color, position) values (?, ?, ?)";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, piece.getName());
            statement.setString(2, piece.getColor().getName());
            statement.setString(3, piece.getPosition().getPosition());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.close(connection);
    }

    public void saveAll(List<Piece> pieces, int gameId) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "insert into piece (name, color, position, game_id) values (?, ?, ?, ?)";
        try {
            for (Piece piece : pieces) {
                final PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, piece.getName());
                statement.setString(2, piece.getColor().getName());
                statement.setString(3, piece.getPosition().getPosition());
                statement.setInt(4, gameId);

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.close(connection);
    }


    public void deleteByPosition(String position) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "delete from piece where position = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.close(connection);
    }

    public void updateByPosition(String source, String target) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "update piece set position = ? where position = ?";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, target);
            statement.setString(2, source);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.close(connection);
    }

    public Pieces findAll() {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "select name, color, position from piece";
        final List<Piece> members = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                members.add(PieceFactory.of(resultSet.getString("name"), resultSet.getString("color"),
                    resultSet.getString("position")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.close(statement, resultSet, connection);
        return new Pieces(members);
    }

    public Piece findByPosition(String position) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "select name, color, position from piece where position = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, position);
            resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException(NON_PIECE_EXCEPTION_MESSAGE);
            }
            return PieceFactory.of(resultSet.getString("name"),
                resultSet.getString("color"),
                resultSet.getString("position"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.close(statement, resultSet, connection);
        return null;
    }


    public void deleteAll() {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "delete from piece";
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        databaseConnector.close(connection);
    }
}
