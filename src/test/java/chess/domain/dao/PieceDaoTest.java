package chess.domain.dao;

import static org.junit.jupiter.api.Assertions.*;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;

class PieceDaoTest {
    @Test
    void delete() throws SQLException {
        final PieceDao pieceDao = new PieceDao();
        pieceDao.deleteAll();
    }

    @Test
    void save() throws SQLException {
        final PieceDao pieceDao = new PieceDao();
        pieceDao.save(new Board().unwrap());
    }
}