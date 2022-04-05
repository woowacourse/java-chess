package chess.dao;

import chess.domain.game.NeoBoard;
import chess.domain.pieces.Color;
import chess.domain.pieces.NeoPiece;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.sql.*;
import java.util.Map;

public class NeoBoardDao {

    private final ConnectionManager connectionManager;

    public NeoBoardDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public NeoBoard save(NeoBoard board) {
        ConnectionFunction<Connection, NeoBoard> runnable  = connection -> {
            final String sql = "insert into neo_board (room_title, turn) values (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, board.getRoomTitle());
            preparedStatement.setString(2, board.getTurn().name());
            preparedStatement.executeUpdate();
            final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (!generatedKeys.next()) {
                throw new IllegalArgumentException("보드 없어요");
            }
            return new NeoBoard(generatedKeys.getInt(1), board.getRoomTitle(), board.getTurn());
        };
        return connectionManager.run(runnable);
    }

    public NeoBoard findById(int id) {
        ConnectionFunction<Connection, NeoBoard> runnable = connection -> {
            final String sql = "select * from neo_board where id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
           return new NeoBoard(
                    resultSet.getInt("id"),
                    resultSet.getString("room_title"),
                    Color.findColor(resultSet.getString("turn"))
            );
        };
        return connectionManager.run(runnable);
    }

    public void init(NeoBoard board, Map<Position, Piece> initialize) {
        ConnectionFunction<Connection, Integer> runnable = connection -> {
            final NeoBoard savedBoard = save(board);
            final NeoPositionDao neoPositionDao = new NeoPositionDao(connectionManager);
            final NeoPieceDao neoPieceDao = new NeoPieceDao(connectionManager);
            neoPositionDao.saveAllPosition(savedBoard.getId());
            for (Position position : initialize.keySet()) {
                int lastPositionId = neoPositionDao.findPositionIdByColumnAndRowAndBoardId(position.getColumn(), position.getRow(), savedBoard.getId());
                final Piece piece = initialize.get(position);
                neoPieceDao.save(new NeoPiece(piece.getType(), piece.getColor(), lastPositionId));
            }
            return null;
        };
        connectionManager.run(runnable);
    }

    public int deleteById(int boardId) {
        ConnectionFunction<Connection, Integer> runnable = connection -> {
            String sql = "delete from neo_board where id=?";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, boardId);
            return preparedStatement.executeUpdate();
        };
        return connectionManager.run(runnable);
    }


    public int deleteAll() {
        ConnectionFunction<Connection, Integer> runnable = connection -> {
            String sql = "delete from neo_board";
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            return preparedStatement.executeUpdate();
        };
        return connectionManager.run(runnable);
    }
}
