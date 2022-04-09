package chess.dao;

import java.sql.PreparedStatement;

public class UserDao {

    public void createUser(final String name, final int boardId) {
        final String sql = "INSERT IGNORE INTO user(name, board_id) VALUES (?, ?)";
        final StatementMaker<PreparedStatement> statementMaker = (statement) -> {
            statement.setString(1, name);
            statement.setInt(2, boardId);
            statement.execute();
        };
        CommonDao.CreateUpdateDelete(sql, statementMaker);
    }

    public int getUser(final String name) {
        final String sql = "SELECT id, board_id FROM user WHERE name=?";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setString(1, name);
        });
        return CommonDao.findId(sql, statementMaker, "id");
    }

    public int getBoard(final int userId) {
        final String sql = "SELECT board_id FROM user WHERE id=?";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setInt(1, userId);
        });
        return CommonDao.findId(sql, statementMaker, "board_id");
    }

    public void deleteUser(final int userId) {
        final String sql = "DELETE FROM user WHERE id=?";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setInt(1, userId);
            statement.execute();
        });
        CommonDao.CreateUpdateDelete(sql, statementMaker);
    }
}
