package chess.repository;

import chess.domain.board.Board;
import chess.domain.board.Status;
import chess.domain.board.StatusType;
import chess.domain.player.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDAO {

    private final RepositoryUtil repositoryUtil;

    public BoardDAO(RepositoryUtil repositoryUtil) {
        this.repositoryUtil = repositoryUtil;
    }

    public void addBoard(User firstUser, User secondUser, Board board) throws SQLException {
        String query = "INSERT INTO board (user1, user2, turn) VALUES (?, ?, ?)";
        String turn = Integer.toString(board.getStatus().getTurn());
        repositoryUtil.executeUpdate(query, firstUser.getName(), secondUser.getName(), turn);
    }

    public int findIdByUsers(User firstUser, User secondUser) throws SQLException {
        ResultSet resultSet = findByUsers(firstUser, secondUser);
        if (!resultSet.next()) {
            throw new IllegalArgumentException(String.format("%s, %s의 대전정보가 존재하지 않습니다.", firstUser.getName(), secondUser.getName()));
        }
        return resultSet.getInt("id");
    }

    public Status findStatusByUsers(User firstUser, User secondUser) throws SQLException {
        ResultSet resultSet = findByUsers(firstUser, secondUser);
        if (!resultSet.next()) {
            throw new IllegalArgumentException(String.format("%s, %s의 대전정보가 존재하지 않습니다.", firstUser.getName(), secondUser.getName()));
        }
        return new Status(resultSet.getInt("turn"), StatusType.PROCESSING);
    }

    public boolean isBoardExist(User firstUser, User secondUser) throws SQLException {
        ResultSet resultSet = findByUsers(firstUser, secondUser);
        return resultSet.next();
    }

    private ResultSet findByUsers(User firstUser, User secondUser) throws SQLException {
        String query = "SELECT * FROM board WHERE user1 = ? AND user2 = ?";
        return repositoryUtil.executeQuery(query, firstUser.getName(), secondUser.getName());
    }

    public int insertOrUpdateIfExist(User firstUser, User secondUser, Board board) throws SQLException {
        ResultSet resultSet1 = findByUsers(firstUser, secondUser);
        if (!resultSet1.next()) {
            addBoard(firstUser, secondUser, board);
            return findIdByUsers(firstUser, secondUser);
        }
        updateByUsers(firstUser, secondUser, board);
        return resultSet1.getInt("id");
    }

    public void updateByUsers(User firstUser, User secondUser, Board modifiedBoard) throws SQLException {
        String query = "UPDATE board SET turn = ? WHERE user1 = ? AND user2 = ?";
        String turn = Integer.toString(modifiedBoard.getStatus().getTurn());
        repositoryUtil.executeUpdate(query, turn, firstUser.getName(), secondUser.getName());
    }

    public void deleteByUsers(User firstUser, User secondUser) throws SQLException {
        String query = "DELETE FROM board WHERE user1 = ? AND user2 = ?";
        repositoryUtil.executeUpdate(query, firstUser.getName(), secondUser.getName());
    }
}
