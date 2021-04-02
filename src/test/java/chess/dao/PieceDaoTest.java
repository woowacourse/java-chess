package chess.dao;

import chess.domain.Turn;
import chess.domain.piece.Pieces;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PieceDaoTest {
    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = new PieceDao();
    }

    @Test
    void clear() throws Exception {
        Pieces pieces = new Pieces();
        pieces.init();
        pieceDao.clear(pieces);
    }

    @Test
    void updateTurn() throws Exception {
        Turn turn = new Turn();
        pieceDao.initTurn();
        pieceDao.updateTurn(turn);
    }
}
