package chess.dao.chess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardDaoImpl implements BoardDao {

    private static final String SERVER = "localhost:13306";
    private static final String DATABASE = "chess";
    private static final String OPTION = "?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void save(final int boardId, final Map<String, String> board) {
        final String query = "INSERT INTO board (board_id, position, piece) VALUES (?, ?,?)";

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {

            for (final Entry<String, String> boardEntry : board.entrySet()) {
                final String position = boardEntry.getKey();
                final String piece = boardEntry.getValue();

                preparedStatement.setInt(1, boardId);
                preparedStatement.setString(2, position);
                preparedStatement.setString(3, piece);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("board 저장 실패");
        }
    }

    @Override
    public Map<String, String> findById(final int boardId) {
        final String query = "SELECT position, piece FROM board WHERE board_id = ?";
        final Map<String, String> board = new HashMap<>();
        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, String.valueOf(boardId));
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                board.put(result.getString("position"), result.getString("piece"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return board;
    }

    @Override
    public void remove(final int boardId) {
        final String query = "DELETE FROM board WHERE board_id = ?";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(boardId));
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
