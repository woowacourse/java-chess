package chess.web.dao.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDaoImpl implements BoardDao {

    private static final String URL = "jdbc:mysql://localhost:13306/chess";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String TABLE = "board";
    private static final String POSITION_COLUMN = "position";
    private static final String PIECE_COLUMN = "piece";


    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void save(final int roomId, final Map<String, String> board) {
        final String sql = ""
            + "INSERT INTO "
            + TABLE + " (roomId, position, piece)"
            + "  VALUES (?,?,?)";
        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            for (final Entry<String, String> boardEntry : board.entrySet()) {
                final String position = boardEntry.getKey();
                final String piece = boardEntry.getValue();
                preparedStatement.setInt(1, roomId);
                preparedStatement.setString(2, position);
                preparedStatement.setString(3, piece);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 board의 기물들 저장하는데 예외가 발생함.");
        }
    }

    @Override
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

    @Override
    public void updateBoard(final int roomId, final Map<String, String> board) {
        final String sql = ""
            + "update " + TABLE
            + " set piece = ?"
            + "  where roomId = ? AND position = ?";
        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
            addBatch(roomId, board, preparedStatement);
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("현재 board의 기물들 업데이트하는데 예외가 발생함.");
        }
    }

    @Override
    public void remove(final int roomId) {
        throw new UnsupportedOperationException("board의 삭제는 room삭제시 자동으로 이루어집니다.");
    }

    private void addBatch(final int roomId, final Map<String, String> board, final PreparedStatement preparedStatement)
        throws SQLException {
        for (final Entry<String, String> boardEntry : board.entrySet()) {
            final String position = boardEntry.getKey();
            final String piece = boardEntry.getValue();
            preparedStatement.setString(1, piece);
            preparedStatement.setInt(2, roomId);
            preparedStatement.setString(3, position);
            preparedStatement.addBatch();
        }
    }
}
