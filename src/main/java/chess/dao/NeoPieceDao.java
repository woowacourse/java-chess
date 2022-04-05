package chess.dao;

import chess.domain.pieces.Color;
import chess.domain.pieces.NeoPiece;
import chess.domain.pieces.Symbol;

import java.sql.*;

public class NeoPieceDao {

    private final ConnectionManager connectionManager;

    public NeoPieceDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public NeoPiece save(NeoPiece neoPiece) {
        ConnectionFunction<Connection, NeoPiece> runnable = (connection) -> {
            final String sql = "insert into neo_piece (type, color, position_id) values (?, ?, ?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, neoPiece.getType().symbol().name());
            preparedStatement.setString(2, neoPiece.getColor().name());
            preparedStatement.setInt(3, neoPiece.getPositionId());
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new IllegalArgumentException("피스를 찾지 못했습니다.");
            }
            return new NeoPiece(generatedKeys.getInt(1), neoPiece.getType(), neoPiece.getColor(), neoPiece.getPositionId());
        };
        return connectionManager.run(runnable);
    }

    public NeoPiece findByPositionId(int positionId) {
        ConnectionFunction<Connection, NeoPiece> runnable = (connection) -> {
            final String sql = "select * from neo_piece where position_id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, positionId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("피스를 찾지 못했습니다.");
            }
            return new NeoPiece(
                    resultSet.getInt("id"),
                    Symbol.findSymbol(resultSet.getString("type")).type(),
                    Color.findColor(resultSet.getString("color")),
                    resultSet.getInt("position_id")
            );
        };
        return connectionManager.run(runnable);
    }

    public Integer updatePiecePositionId(int sourcePositionId, int targetPositionId) {
        ConnectionFunction<Connection, Integer> runnable = (connection) -> {
            final String sql = "update neo_piece set position_id=? where position_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, targetPositionId);
            preparedStatement.setInt(2, sourcePositionId);
            return preparedStatement.executeUpdate();
        };
        return connectionManager.run(runnable);
    }

    public int deletePieceByPositionId(int positionId) {
        ConnectionFunction<Connection, Integer> runnable = (connection) -> {
            final String sql = "delete from neo_piece where position_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, positionId);
            return preparedStatement.executeUpdate();
        };
        return connectionManager.run(runnable);
    }

    public int deleteAll() {
        ConnectionFunction<Connection, Integer> runnable = connection -> {
            String sql = "delete from neo_piece";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            return preparedStatement.executeUpdate();
        };
        return connectionManager.run(runnable);
    }
}
