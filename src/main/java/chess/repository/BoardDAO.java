package chess.repository;

import chess.domain.board.Board;
import chess.domain.board.Status;
import chess.domain.board.StatusType;
import chess.domain.player.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BoardDAO {

    private static final String NO_BOARD_MESSAGE = "%s, %s의 대전정보가 존재하지 않습니다.";

    private final DBConnector DBConnector;

    public BoardDAO(DBConnector DBConnector) {
        this.DBConnector = DBConnector;
    }

    public void addBoard(User firstUser, User secondUser, Board board) throws SQLException {
        String query = "INSERT INTO board (white, black, turn) VALUES (?, ?, ?)";
        String turn = Integer.toString(board.getStatus().getTurn());
        DBConnector.executeUpdate(query, firstUser.getName(), secondUser.getName(), turn);
    }

    public int findIdByUsers(User firstUser, User secondUser) throws SQLException {
        ResultSet resultSet = findByUsers(firstUser, secondUser);
        if (!resultSet.next()) {
            throw new IllegalArgumentException(String.format(NO_BOARD_MESSAGE, firstUser.getName(), secondUser.getName()));
        }
        return resultSet.getInt("id");
    }

    public Status findStatusByUsers(User firstUser, User secondUser) throws SQLException {
        ResultSet resultSet = findByUsers(firstUser, secondUser);
        if (!resultSet.next()) {
            throw new IllegalArgumentException(String.format(NO_BOARD_MESSAGE, firstUser.getName(), secondUser.getName()));
        }
        return new Status(resultSet.getInt("turn"), StatusType.PROCESSING);
    }

    private ResultSet findByUsers(User firstUser, User secondUser) throws SQLException {
        String query = "SELECT * FROM board WHERE white = ? AND black = ?";
        return DBConnector.executeQuery(query, firstUser.getName(), secondUser.getName());
    }

    public int upsert(User firstUser, User secondUser, Board board) throws SQLException {
        String query = "INSERT INTO board (white, black, turn) VALUES (?, ?, ?)" +
                "ON DUPLICATE KEY UPDATE turn = ?;";
        String turn = Integer.toString(board.getStatus().getTurn());
        ResultSet resultSet = DBConnector.executeUpdate(query, firstUser.getName(), secondUser.getName(), turn, turn);

        if (!resultSet.next()) {
            throw new IllegalArgumentException(String.format(NO_BOARD_MESSAGE, firstUser.getName(), secondUser.getName()));
        }
        return resultSet.getInt(Statement.RETURN_GENERATED_KEYS);
    }

    public void updateByUsers(User firstUser, User secondUser, Board modifiedBoard) throws SQLException {
        String query = "UPDATE board SET turn = ? WHERE white = ? AND black = ?";
        String turn = Integer.toString(modifiedBoard.getStatus().getTurn());
        DBConnector.executeUpdate(query, turn, firstUser.getName(), secondUser.getName());
    }

    public void deleteByUsers(User firstUser, User secondUser) throws SQLException {
        String query = "DELETE FROM board WHERE white = ? AND black = ?";
        DBConnector.executeUpdate(query, firstUser.getName(), secondUser.getName());
    }
}
