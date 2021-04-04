package chess.dao;

import chess.domain.Turn;
import chess.domain.piece.Pieces;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PieceDaoTest {
    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        pieceDao = new PieceDao();
    }

    @Test
    @DisplayName("db 초기세팅이 잘 되는지 확인")
    void clear() throws Exception {
        Pieces pieces = new Pieces();
        pieces.init();
        pieceDao.clear(pieces);
    }

    @Test
    @DisplayName("바뀐 턴이 db에 잘 저장되는지 확인")
    void updateTurn() throws Exception {
        Turn turn = new Turn();
        pieceDao.initTurn();
        pieceDao.updateTurn(turn);
    }
}
