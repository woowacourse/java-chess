package chess.dao;

import chess.dao.initialboard.InitialBoard;
import chess.domain.GameState;
import java.sql.PreparedStatement;
import java.util.Map;

public class BoardDao {

    public int initBoard() {
        final String sql = "INSERT INTO pieces(piece_id, position, board_id) VALUES (?, ?, ?);";
        createNewBoard();
        final int boardId = getLastCreatedBoardId();
        final Map<String, Integer> pieces = InitialBoard.getInitialPiecesIdAndLocation();
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            for (Map.Entry<String, Integer> positionAndPieceId : pieces.entrySet()) {
                statement.setInt(1, positionAndPieceId.getValue());
                statement.setString(2, positionAndPieceId.getKey());
                statement.setInt(3, boardId);
                statement.execute();
            }
        });
        CommonDao.CreateUpdateDelete(sql, statementMaker);
        return boardId;
    }

    private void createNewBoard() {
        final String sql = "INSERT INTO board(game_status) VALUES ('READY');";
        final StatementMaker<PreparedStatement> statementMaker = (PreparedStatement::execute);
        CommonDao.CreateUpdateDelete(sql, statementMaker);
    }

    private int getLastCreatedBoardId() {
        final String sql = "SELECT id FROM board ORDER BY id DESC LIMIT 1";
        final StatementMaker<PreparedStatement> statementMaker = (PreparedStatement::execute);
        return CommonDao.findId(sql, statementMaker, "id");
    }

    public GameState getGameStatus(final int userId) {
        final String sql = "SELECT b.game_status FROM user LEFT JOIN board b on user.board_id = b.id WHERE user.id=?;";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setInt(1, userId);
        });
        return CommonDao.getGameStatus(sql, statementMaker);
    }

    public void changeGameStatus(final String gameStatus, final int boardId) {
        final String sql = "UPDATE board SET game_status=? WHERE id=?;";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setString(1, gameStatus);
            statement.setInt(2, boardId);
            statement.execute();
        });
        CommonDao.CreateUpdateDelete(sql, statementMaker);
    }

    public void deleteBoard(final int id) {
        final String sql = "DELETE FROM board WHERE id=?;";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setInt(1, id);
            statement.execute();
        });
        CommonDao.CreateUpdateDelete(sql, statementMaker);
    }
}
