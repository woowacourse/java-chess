package chess.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {
    @Test
    void 체스판() {
        ChessBoard chessBoard = new ChessBoard();
        assertEquals("RNBQKBNR\n" +
                "PPPPPPPP\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "pppppppp\n" +
                "rnbqkbnr\n", chessBoard.status());
    }

    @Test
    void move_테스트() {
        ChessBoard chessBoard = new ChessBoard();
        assertThrows(IllegalArgumentException.class, () -> chessBoard.move("b2", "b2"));
    }

    @Test
    void move_테스트2() {
        ChessBoard chessBoard = new ChessBoard();
        assertThrows(IllegalArgumentException.class, () -> chessBoard.move("b2", "z2"));
    }

    @Test
    void move_테스트3() {
        ChessBoard chessBoard = new ChessBoard();
        assertThrows(IllegalArgumentException.class, () -> chessBoard.move("a0", "b2"));
    }

    @Test
    void move_테스트4() {
        ChessBoard chessBoard = new ChessBoard();
        assertThrows(IllegalArgumentException.class, () -> chessBoard.move("j3", "p7"));
    }
}