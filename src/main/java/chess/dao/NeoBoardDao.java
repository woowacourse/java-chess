package chess.dao;

import chess.domain.game.NeoBoard;
import chess.domain.pieces.Color;
import chess.domain.pieces.NeoPiece;
import chess.domain.pieces.Piece;
import chess.domain.position.NeoPosition;
import chess.domain.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class NeoBoardDao {

    private final ConnectionManager connectionManager;

    public NeoBoardDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    public void save(NeoBoard board) {
        final Connection connection = connectionManager.getConnection();
        final String sql = "insert into neo_board (room_title, turn) values (?, ?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, board.getRoomTitle());
            preparedStatement.setString(2, board.getTurn().name());
            preparedStatement.executeUpdate();
            connectionManager.close(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
        save(board);
        Connection connection = connectionManager.getConnection();
        final NeoPositionDao neoPositionDao = new NeoPositionDao(connectionManager);
        final NeoPieceDao neoPieceDao = new NeoPieceDao(connectionManager);
        int lastNeoBoardId = findLastNeoBoardId();
        for (Position position : initialize.keySet()) {
            neoPositionDao.save(connection, new NeoPosition(position.getColumn(), position.getRow(), lastNeoBoardId));
            int lastPositionId = neoPositionDao.findLastPositionId(connection);
            final Piece piece = initialize.get(position);
            neoPieceDao.save(connection, new NeoPiece(piece.getType(), piece.getColor(), lastPositionId));
        }
        connectionManager.close(connection);
    }

    private int findLastNeoBoardId() {
        final Connection connection = connectionManager.getConnection();
        final String sql = "select id from neo_board order by id desc limit 1";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new SQLException();
            }
            final int id = resultSet.getInt("id");
            connectionManager.close(connection);
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
