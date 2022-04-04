package chess.dao;

import chess.domain.pieces.Color;
import chess.domain.pieces.NeoPiece;
import chess.domain.pieces.Symbol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NeoPieceDao {

    private final ConnectionManager connectionManager;

    public NeoPieceDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void save(NeoPiece neoPiece) {
        final Connection connection = connectionManager.getConnection();
        final String sql = "insert into neo_piece (type, color, position_id) values (?, ?, ?)";
        try {
            savePiece(connection, neoPiece);
            connectionManager.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Connection connection, NeoPiece neoPiece) {
        try {
            savePiece(connection, neoPiece);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void savePiece(Connection connection, NeoPiece neoPiece) throws SQLException {
        final String sql = "insert into neo_piece (type, color, position_id) values (?, ?, ?)";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, neoPiece.getType().symbol().name());
        preparedStatement.setString(2, neoPiece.getColor().name());
        preparedStatement.setInt(3, neoPiece.getPositionId());
        preparedStatement.executeUpdate();
    }

    public NeoPiece findByPositionId(int positionId) {
        final String sql = "select * from neo_piece where position_id=?";
        try (final Connection connection = connectionManager.getConnection();
             final PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, positionId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return new NeoPiece(
                    resultSet.getInt("id"),
                    Symbol.findSymbol(resultSet.getString("type")).type(),
                    Color.findColor(resultSet.getString("color")),
                    resultSet.getInt("position_id")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
