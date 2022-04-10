package chess.dao;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessDao {

    static final int GAME_ID = 0;

    static {
        try {
            if (!hasAlreadyGame()) {
                insertGame();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean hasAlreadyGame() throws SQLException {
        final var sql = "SELECT COUNT(*) from game WHERE game_id = ?";
        final var prepareStatement = ChessConnection.getConnection().prepareStatement(sql);
        prepareStatement.setInt(1, GAME_ID);
        final ResultSet resultSet = prepareStatement.executeQuery();
        if (resultSet.next()) {
            final var count = Integer.parseInt(resultSet.getString(1));
            return count != 0;
        }
        throw new SQLException("game_id: " + GAME_ID + "를 조회할수 없습니다.");
    }

    private static void insertGame() throws SQLException {
        final var sql = "INSERT INTO game (game_id) VALUES(?)";
        final var prepareStatement = ChessConnection.getConnection().prepareStatement(sql);
        prepareStatement.setInt(1, GAME_ID);
        prepareStatement.execute();
    }

    private final Connection connection = ChessConnection.getConnection();

    public void initBoard(final Map<Position, Piece> board) {
        try {
            initTurn();
            deleteAllPiece();
            savePiece(board);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void initTurn() {
        updateTurn("WHITE");
    }

    public void updateTurn(final String color) {
        final var sql = "UPDATE game SET current_turn=? WHERE game_id=?";
        final PreparedStatement prepareStatement;
        try {
            prepareStatement = ChessConnection.getConnection().prepareStatement(sql);
            prepareStatement.setString(1, color);
            prepareStatement.setInt(2, GAME_ID);
            prepareStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public Map<String, List<String>> findBoard() {
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

    public String findCurrentColor() {
        final var sql = "SELECT current_turn FROM game WHERE game_id=?";
        final PreparedStatement prepareStatement;
        try {
            prepareStatement = ChessConnection.getConnection().prepareStatement(sql);
            prepareStatement.setInt(1, GAME_ID);
            final ResultSet resultSet = prepareStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(1);
            }
            throw new SQLException("현재 순서의 색상을 조회할 수 없습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateBoard(final String from, final String to, final String color) {
        try {
            deletePiece(to);
            updatePiece(from, to);
            updateTurn(color);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deletePiece(final String to) throws SQLException {
        final var sql = "DELETE FROM piece WHERE position = ?";
        final var statement = connection.prepareStatement(sql);
        statement.setString(1, to);
        statement.execute();
    }

    private void updatePiece(final String from, final String to) throws SQLException {
        final var sql = "UPDATE piece SET position = ? WHERE position = ? AND game_id = ?";
        final var statement = connection.prepareStatement(sql);
        statement.setString(1, to);
        statement.setString(2, from);
        statement.setInt(3, GAME_ID);
        statement.execute();
    }
}
