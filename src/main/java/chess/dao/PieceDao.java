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

    private final DatabaseConnector databaseConnector = new DatabaseConnector();

    public void save(Piece piece) {
        final Connection connection = databaseConnector.getConnection();

        PreparedStatement statement = executeInsertStatement(piece, connection);

        databaseConnector.close(statement, connection);
    }

    private PreparedStatement executeInsertStatement(Piece piece, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("insert into piece (name, color, position) values (?, ?, ?)");
            statement.setString(1, piece.getName());
            statement.setString(2, piece.getColor().getName());
            statement.setString(3, piece.getPosition().getPosition());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }


    public void saveAll(List<Piece> pieces) {
        final Connection connection = databaseConnector.getConnection();
        PreparedStatement statement = null;

        for (Piece piece : pieces) {
            statement = executeInsertStatement(piece, connection);
        }

        databaseConnector.close(statement, connection);
    }


    public void deleteByPosition(String position) {
        final Connection connection = databaseConnector.getConnection();

        PreparedStatement statement = executeDeleteByPositionStatement(position, connection);

        databaseConnector.close(statement, connection);
    }

    private PreparedStatement executeDeleteByPositionStatement(String position, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("delete from piece where position = ?");
            statement.setString(1, position);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public void updateByPosition(String source, String target) {
        final Connection connection = databaseConnector.getConnection();

        PreparedStatement statement = executeUpdateByPositionStatement(source, target, connection);

        databaseConnector.close(statement, connection);
    }

    private PreparedStatement executeUpdateByPositionStatement(String source, String target, Connection connection) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(
                "update piece set position = ? where position = ?");
            statement.setString(1, target);
            statement.setString(2, source);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    public Pieces findAll() {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "select name, color, position from piece";
        PreparedStatement statement = executeStatement(connection, sql);

        ResultSet resultSet = executeQueryAndgetResultSet(connection, statement);
        final List<Piece> pieces = getPiecesFromResultSet(resultSet);

        databaseConnector.close(statement, resultSet, connection);

        return new Pieces(pieces);
    }

    private PreparedStatement executeStatement(Connection connection, String sql) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

    private ResultSet executeQueryAndgetResultSet(Connection connection, PreparedStatement statement) {
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    private List<Piece> getPiecesFromResultSet(ResultSet resultSet) {
        final List<Piece> pieces = new ArrayList<>();
        try {
            while (resultSet.next()) {
                pieces.add(PieceFactory.of(
                    resultSet.getString("name")
                    , resultSet.getString("color"),
                    resultSet.getString("position")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(pieces);
    }

    public Piece findByPosition(String position) {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "select name, color, position from piece where position = ?";

        PreparedStatement statement = executeSelectByPositionStatement(connection, sql, position);
        ResultSet resultSet = executeQueryAndgetResultSet(connection, statement);

        Piece piece = getPieceFromResultSet(resultSet);

        databaseConnector.close( statement, resultSet, connection);

        return piece;
    }

    private Piece getPieceFromResultSet(ResultSet resultSet) {
        try {
            if (!resultSet.next()) {
                throw new IllegalArgumentException("해당위치에는 말이 없습니다.");
            }
            return PieceFactory.of(
                resultSet.getString("name"),
                resultSet.getString("color"),
                resultSet.getString("position"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private PreparedStatement executeSelectByPositionStatement(Connection connection, String sql, String position) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, position);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }


    public void deleteAll() {
        final Connection connection = databaseConnector.getConnection();
        final String sql = "delete from piece";

        PreparedStatement statement = executeStatement(connection, sql);

        databaseConnector.close(statement, connection);
    }
}
