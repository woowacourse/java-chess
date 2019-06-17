package chess.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChessBoardTest {

    @Test
    void status() {
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.status();
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

    @Test
    void access() {
        ChessBoard chessBoard = new ChessBoard();
        assertEquals(new Position(ChessTeam.WHITE, new QueenPiece("p")), chessBoard.access("b2"));
    }

    @Test
    void access2() {
        ChessBoard chessBoard = new ChessBoard();
        assertEquals(new Position(ChessTeam.BLACK, new QueenPiece("R")), chessBoard.access("a8"));
    }

    @Test
    void access3() {
        ChessBoard chessBoard = new ChessBoard();
        assertEquals(new Position(ChessTeam.WHITE, new QueenPiece("r")), chessBoard.access("h1"));
    }
}