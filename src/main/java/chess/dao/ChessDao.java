package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessDao {

    private static final int GAME_ID = 0;

    static {
        if (!hasAlreadyGame()) {
            insertGame();
        }
    }

    private static boolean hasAlreadyGame() {
        final var sql = "SELECT COUNT(*) from game WHERE game_id = ?";
        var count = 0;
        try (final var connection = ChessConnection.getConnection();
             final var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setInt(1, GAME_ID);
            try (final var resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    count = Integer.parseInt(resultSet.getString(1));
                }
                return count != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void insertGame() {
        final var sql = "INSERT INTO game (game_id) VALUES(?)";
        try (final var connection = ChessConnection.getConnection();
             final var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setInt(1, GAME_ID);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initBoard(final Map<Position, Piece> board) {
        initTurn();
        deleteAllPiece();
        savePiece(board);
    }

    public void initTurn() {
        updateTurn("WHITE");
    }

    public void updateTurn(final String color) {
        final var sql = "UPDATE game SET current_turn=? WHERE game_id=?";
        try (final var connection = ChessConnection.getConnection();
             final var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setString(1, color);
            prepareStatement.setInt(2, GAME_ID);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllPiece() {
        final var sql = "DELETE FROM piece WHERE game_id = ?";
        try (final var connection = ChessConnection.getConnection();
             final var prepareStatement = connection.prepareStatement(sql);) {
            prepareStatement.setInt(1, GAME_ID);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void savePiece(final Map<Position, Piece> board) {
        final var sql = "INSERT INTO piece (game_id, piece_name, piece_color, position) VALUES(?,?,?,?)";
        try (final var connection = ChessConnection.getConnection();
             final var prepareStatement = connection.prepareStatement(sql)) {
            for (final Map.Entry<Position, Piece> entry : board.entrySet()) {
                prepareStatement.setInt(1, GAME_ID);
                prepareStatement.setString(2, entry.getValue().getNotation().name());
                prepareStatement.setString(3, entry.getValue().getColor().name());
                prepareStatement.setString(4, entry.getKey().getFile().name() + entry.getKey().getRankNumber());
                prepareStatement.addBatch();
            }
            prepareStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Map<String, List<String>> findBoard() {
        final var sql = "SELECT * FROM piece WHERE game_id=?";
        try (final var connection = ChessConnection.getConnection();
             final var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setInt(1, GAME_ID);
            try (final var resultSet = prepareStatement.executeQuery()) {
                final Map<String, List<String>> boardData = new HashMap<>();
                while (resultSet.next()) {
                    boardData.put(resultSet.getString("position"),
                            List.of(
                                    resultSet.getString("piece_name"),
                                    resultSet.getString("piece_color")
                            ));
                }
                return boardData;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public String findCurrentColor() {
        final var sql = "SELECT current_turn FROM game WHERE game_id=?";
        try (final var connection = ChessConnection.getConnection();
             final var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setInt(1, GAME_ID);
            try (final var resultSet = prepareStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString(1);
                }
                throw new SQLException("현재 순서의 색상을 조회할 수 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateBoard(final String from, final String to, final String color) {
        deletePiece(to);
        updatePiece(from, to);
        updateTurn(color);
    }

    private void deletePiece(final String to) {
        final var sql = "DELETE FROM piece WHERE position = ?";
        try (final var connection = ChessConnection.getConnection();
             final var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setString(1, to);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePiece(final String from, final String to) {
        final var sql = "UPDATE piece SET position = ? WHERE position = ? AND game_id = ?";
        try (final var connection = ChessConnection.getConnection();
             final var prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setString(1, to);
            prepareStatement.setString(2, from);
            prepareStatement.setInt(3, GAME_ID);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
