package chess.domain.dao;

import chess.domain.Color;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class PieceDaoImpl implements PieceDao {

    @Override
    public void save(Map<Position, Piece> board, int boardId) {
        Connection connection = DBConnector.getConnection();
        if (isExistsPieces()) {
            updatePieces(board, boardId, connection);
            return;
        }
        insertPiece(board, boardId, connection);
    }

    private void insertPiece(Map<Position, Piece> board, int boardId, Connection connection) {
        final String sql = "insert into piece (board_id, position, type, color) values (?, ?, ?, ?)";
        for (Entry<Position, Piece> positionPieceEntry : board.entrySet()) {
            Position position = positionPieceEntry.getKey();
            Piece piece = positionPieceEntry.getValue();
            try {
                final PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, boardId);
                statement.setString(2, position.stringName());
                statement.setString(3, piece.getSymbol());
                statement.setInt(4, piece.getColor().ordinal());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void updatePieces(Map<Position, Piece> board, int boardId, Connection connection) {
        final String sql = "update piece set board_id = ?, type = ?, color = ? where position = ?";
        for (Entry<Position, Piece> positionPieceEntry : board.entrySet()) {
            Position position = positionPieceEntry.getKey();
            Piece piece = positionPieceEntry.getValue();
            try {
                final PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, boardId);
                statement.setString(2, piece.getSymbol());
                statement.setInt(3, piece.getColor().ordinal());
                statement.setString(4, position.stringName());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Map<Position, Piece> load() {
        final Connection connection = DBConnector.getConnection();
        final String sql = "select position, type, color from piece where board_id = ?";
        Map<Position, Piece> pieces = new TreeMap<>();
        try {
            final PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 1);
            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Position position = Position.from(resultSet.getString("position"));
                Type type = Type.from(resultSet.getString("type"));
                Piece piece = type.makePiece(Color.from(resultSet.getInt("color")));
                pieces.put(position, piece);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    @Override
    public boolean isExistsPieces() {
        final String sql = "select id from piece where board_id = ?";
        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, 1);
            final ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteByBoardId(int boardId) {
        final String sql = "delete from piece where board_id = ?";
        try (final Connection connection = DBConnector.getConnection();
             final PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, boardId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
