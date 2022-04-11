package chess.web.dao.board;

import chess.web.dto.BoardDto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDao {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String TABLE = "board";
    private static final String POSITION_COLUMN = "position";
    private static final String PIECE_COLUMN = "piece";
    private static final int PIECE_COLUMN_NUMBER = 1;
    private static final int POSITION_COLUMN_NUMBER = 2;

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void save(final int roomId, final Map<String, String> board) {
        final String sql = "" + "INSERT INTO " + TABLE + " (roomId, position, piece)" + "  VALUES (?,?,?)"; // 달라짐
        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            for (final Entry<String, String> boardEntry : board.entrySet()) {
                final String position = boardEntry.getKey();
                final String piece = boardEntry.getValue();
                preparedStatement.setInt(1, roomId);
                preparedStatement.setString(2, piece);
                preparedStatement.setString(3, position);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 board의 기물들 저장하는데 예외가 발생함.");
        }
    }

    public Map<String, String> findAll() {
        final String sql = ""
            + "SELECT position, piece"
            + "  FROM " + TABLE;
        final Map<String, String> board = new HashMap<>();
        try (final PreparedStatement statement = getConnection().prepareStatement(sql);
             final ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                board.put(
                    resultSet.getString(POSITION_COLUMN),
                    resultSet.getString(PIECE_COLUMN)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return board;
    }

    public void update(final Map<String, String> board) {
        final String sql = ""
            + "UPDATE " + TABLE
            + "  SET piece = ?"
            + "  WHERE position = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            addBatchEveryRow(board, preparedStatement);
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(final int roomId, final BoardDto board) {
        final String sql = ""
            + "UPDATE " + TABLE
            + "  SET piece = ?"
            + "  WHERE position = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            addBatchEveryRow(board.getBoard(), preparedStatement);
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addBatchEveryRow(final Map<String, String> board, final PreparedStatement preparedStatement)
        throws SQLException {
        for (final Entry<String, String> boardEntry : board.entrySet()) {
            final String position = boardEntry.getKey();
            final String piece = boardEntry.getValue();
            preparedStatement.setString(PIECE_COLUMN_NUMBER, piece);
            preparedStatement.setString(POSITION_COLUMN_NUMBER, position);
            preparedStatement.addBatch();
        }
    }

    public void updatePiecePosition(final String position, final String piece) {
        final String sql = ""
            + "UPDATE " + TABLE
            + "  SET piece = ?"
            + "  WHERE position = ?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            preparedStatement.setString(PIECE_COLUMN_NUMBER, piece);
            preparedStatement.setString(POSITION_COLUMN_NUMBER, position);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
