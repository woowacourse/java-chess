package domain.chess;

import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    void name() {
        Board board = new Board(PieceFactory.createPieces());
        board.getStatus();
    }
}