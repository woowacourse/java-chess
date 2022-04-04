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
        final Connection connection = connectionManager.getConnection();
        try {
            final ResultSet generatedKeys = saveBoard(connection, board);
            final NeoBoard neoBoard = new NeoBoard(generatedKeys.getInt(1), board.getRoomTitle());
            connectionManager.close(connection);
            return neoBoard;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public NeoBoard save(Connection connection, NeoBoard board) {
        try {
            final ResultSet generatedKeys = saveBoard(connection, board);
            return new NeoBoard(generatedKeys.getInt(1), board.getRoomTitle());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ResultSet saveBoard(Connection connection, NeoBoard board) throws SQLException {
        final String sql = "insert into neo_board (room_title, turn) values (?, ?)";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, board.getRoomTitle());
        preparedStatement.setString(2, board.getTurn().name());
        preparedStatement.executeUpdate();
        final ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (!generatedKeys.next()) {
            throw new IllegalArgumentException("did not save");
        }
        return generatedKeys;
    }

    public NeoBoard findById(int id) {
        final Connection connection = connectionManager.getConnection();
        final String sql = "select * from neo_board where id=?";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            final NeoBoard neoBoard = new NeoBoard(
                    resultSet.getInt("id"),
                    resultSet.getString("room_title"),
                    Color.findColor(resultSet.getString("turn"))
            );
            connectionManager.close(connection);
            return neoBoard;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void init(NeoBoard board, Map<Position, Piece> initialize) {
        Connection connection = connectionManager.getConnection();
        final NeoBoard savedBoard = save(connection, board);
        final NeoPositionDao neoPositionDao = new NeoPositionDao(connectionManager);
        final NeoPieceDao neoPieceDao = new NeoPieceDao(connectionManager);
        neoPositionDao.saveAllPosition(connection, savedBoard.getId());
        for (Position position : initialize.keySet()) {
            int lastPositionId = neoPositionDao.findPositionIdByColumnAndRowAndBoardId(connection, position.getColumn(), position.getRow(), savedBoard.getId());
            final Piece piece = initialize.get(position);
            neoPieceDao.save(connection, new NeoPiece(piece.getType(), piece.getColor(), lastPositionId));
        }
        connectionManager.close(connection);
    }
}
