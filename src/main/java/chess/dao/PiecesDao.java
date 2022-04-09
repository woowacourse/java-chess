package chess.dao;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.sql.PreparedStatement;
import java.util.Map;

public class PiecesDao {

    public Map<Position, Piece> getPieces(final int boardId) {
        final String sql = "SELECT ps.position, p.symbol, p.color, p.image_url FROM pieces AS ps\n"
                + "    LEFT JOIN piece p on ps.piece_id = p.id\n"
                + "WHERE board_id=?;";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setInt(1, boardId);
        });
        return CommonDao.getPieces(sql, statementMaker);
    }

    public void changePosition(final String currentPosition, final String targetPosition, final int boardId) {
        final String sql = "UPDATE pieces SET position=? WHERE position=? AND board_id=?;";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setString(1, targetPosition);
            statement.setString(2, currentPosition);
            statement.setInt(3, boardId);
            statement.execute();
        });
        CommonDao.CreateUpdateDelete(sql, statementMaker);
    }

    public void deletePiece(final String position) {
        final String sql = "DELETE FROM pieces WHERE position=?;";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setString(1, position);
            statement.execute();
        });
        CommonDao.CreateUpdateDelete(sql, statementMaker);
    }

    public void deletePieces(final int boardId) {
        final String sql = "DELETE FROM pieces WHERE board_id=?;";
        final StatementMaker<PreparedStatement> statementMaker = (statement -> {
            statement.setInt(1, boardId);
            statement.execute();
        });
        CommonDao.CreateUpdateDelete(sql, statementMaker);
    }
}
