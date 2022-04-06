package chess.dao;

import chess.domain.pieces.Color;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Symbol;
import chess.domain.position.Column;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PieceDao {

    private final ConnectionManager connectionManager;

    public PieceDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public Piece save(Piece piece) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "insert into piece (type, color, position_id) values (?, ?, ?)";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, piece.getType().symbol().name());
            preparedStatement.setString(2, piece.getColor().name());
            preparedStatement.setInt(3, piece.getPositionId());
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new IllegalArgumentException("피스를 찾지 못했습니다.");
            }
            return new Piece(generatedKeys.getInt(1), piece.getColor(), piece.getType(), piece.getPositionId());
        });
    }

    public Optional<Piece> findByPositionId(int positionId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "select * from piece where position_id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, positionId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(new Piece(
                    resultSet.getInt("id"),
                    Color.findColor(resultSet.getString("color")),
                    Symbol.findSymbol(resultSet.getString("type")).type(),
                    resultSet.getInt("position_id")));
        });
    }

    public int updatePiecePositionId(int sourcePositionId, int targetPositionId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "update piece set position_id=? where position_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, targetPositionId);
            preparedStatement.setInt(2, sourcePositionId);
            return preparedStatement.executeUpdate();
        });
    }

    public int deletePieceByPositionId(int positionId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "delete from piece where position_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, positionId);
            return preparedStatement.executeUpdate();
        });
    }

    public List<Piece> getAllPiecesByBoardId(int boardId) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "select pi.id, pi.type, pi.color, pi.position_id from piece pi join position po on pi.position_id=po.id " +
                    "join board nb on po.board_id=nb.id where nb.id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, boardId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            List<Piece> pieces = new ArrayList<>();
            while (resultSet.next()) {
                pieces.add(new Piece(
                        resultSet.getInt("id"),
                        Color.findColor(resultSet.getString("color")),
                        Symbol.findSymbol(resultSet.getString("type")).type(),
                        resultSet.getInt("position_id")
                ));
            }
            return pieces;
        });
    }

    public int countPawnsOnSameColumn(int roomId, Column column, Color color) {
        return connectionManager.executeQuery(connection -> {
            final String sql = "select count(*) as total_count from piece pi " +
                    "join position po on pi.position_id = po.id " +
                    "where po.position_column=? and po.board_id=? and pi.type='PAWN' and pi.color=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, column.value());
            preparedStatement.setInt(2, roomId);
            preparedStatement.setString(3, color.name());
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalArgumentException("쿼리가 잘못됐습니다.");
            }

            return resultSet.getInt("total_count");
        });
    }
}
