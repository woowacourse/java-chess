package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.dao.GameDao.GAME_ID;

public final class PieceDao {

    private final Connection connection = ChessConnection.getConnection();

    public void initBoard(final Map<Position, Piece> board) {
        try {
            deleteAllPiece();
            savePiece(board);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void savePiece(final Map<Position, Piece> board) throws SQLException {
        final var sql = "INSERT INTO piece (game_id, piece_name, piece_color, position) VALUES(?,?,?,?)";
        final PreparedStatement statement = connection.prepareStatement(sql);
        for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
            statement.setInt(1, GAME_ID);
            statement.setString(2, entry.getValue().getNotation().name());
            statement.setString(3, entry.getValue().getColor().name());
            statement.setString(4, entry.getKey().getFile().name() + entry.getKey().getRankNumber());
            statement.addBatch();
        }
        statement.executeBatch();
    }

    public void updatePiece(final Position from, final Position to) {
        try {
            delete(to);
            update(from, to);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void delete(final Position to) throws SQLException {
        final var sql = "DELETE FROM piece WHERE position = ?";
        final var statement = connection.prepareStatement(sql);
        statement.setString(1, to.getFile().name() + to.getRankNumber());
        statement.execute();
    }

    private void update(final Position from, final Position to) throws SQLException {
        final var sql = "UPDATE piece SET position = ? WHERE position = ? AND game_id = ?";
        final var statement = connection.prepareStatement(sql);
        statement.setString(1, to.getFile().name() + to.getRankNumber());
        statement.setString(2, from.getFile().name() + from.getRankNumber());
        statement.setInt(3, GAME_ID);
        statement.execute();
    }

    public void deleteAllPiece() {
        final var sql = "DELETE FROM piece WHERE game_id = ?";
        final PreparedStatement statement;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, GAME_ID);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Map<String, List<String>> findAllPiece() {
        final var sql = "SELECT * FROM piece WHERE game_id=?";
        final PreparedStatement prepareStatement;
        try {
            prepareStatement = ChessConnection.getConnection().prepareStatement(sql);
            prepareStatement.setInt(1, GAME_ID);
            final var resultSet = prepareStatement.executeQuery();
            final Map<String, List<String>> boardData = new HashMap<>();
            while (resultSet.next()) {
                boardData.put(resultSet.getString("position"),
                        List.of(
                                resultSet.getString("piece_name"),
                                resultSet.getString("piece_color")
                        ));
            }
            return boardData;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
