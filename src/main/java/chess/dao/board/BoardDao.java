package chess.dao.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDao {

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

    public void save(final int boardId, final String position, final String piece, final boolean isLowerTeamTurn) {
        final String query = "INSERT INTO board (board_id, position, piece, isLowerTeamTurn) VALUES (?, ?, ?, ?)";

        try (final PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {

            preparedStatement.setInt(1, boardId);
            preparedStatement.setString(2, position);
            preparedStatement.setString(3, piece);
            preparedStatement.setBoolean(4, isLowerTeamTurn);

            preparedStatement.addBatch();
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("board 저장 실패");
        }
    }

    public List<String> findById(final int boardId) {
        final String query = "SELECT position, piece FROM board WHERE board_id = ?";
        List<String> positionsWithPieces = new ArrayList<>();

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(boardId));

            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                positionsWithPieces.add(result.getString("position"));
                positionsWithPieces.add(result.getString("piece"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return positionsWithPieces;
    }

    public boolean isLowerTeamTurnByBoardId(final int boardId) {
        final String query = "SELECT isLowerTeamTurn FROM board WHERE board_id = ?";

        try (final var connection = getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, String.valueOf(boardId));

            final ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                return result.getBoolean("isLowerTeamTurn");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

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
