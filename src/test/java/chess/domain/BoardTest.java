package chess.domain;

import chess.domain.pieces.Empty;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Rook;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {

    @Test
    void 정상_이동_테스트() {
        Board board = new Board();
        System.out.println(board.get(new Point(2, 2)));
        board.set(new Point(2, 2), new Pawn(ChessTeam.WHITE));
        board.play(new Point(2, 2), new Point(2, 3));
        assertEquals(new Pawn(ChessTeam.WHITE), board.get(new Point(2, 3)));
    }

    @Test
    void 이동_불가_테스트() {
        Board board = new Board();
        board.set(new Point(2, 2), new Pawn(ChessTeam.BLACK));
        assertThrows(IllegalArgumentException.class, () -> board.play(new Point(2, 2), new Point(2, 5)));
    }

    @Test
    void 이동_불가_테스트2() {
        Board board = new Board();
        board.set(new Point(2, 2), new Pawn(ChessTeam.BLACK));
        board.set(new Point(2, 3), new Pawn(ChessTeam.BLACK));
        assertThrows(IllegalArgumentException.class, () -> board.play(new Point(2, 2), new Point(2, 3)));
    }

    @Test
    void 이동_불가_테스트3() {
        Board board = new Board();
        board.set(new Point(2, 2), new Pawn(ChessTeam.BLACK));
        assertThrows(IllegalArgumentException.class, () -> board.play(new Point(2, 2), new Point(3, 3)));
    }

    @Test
    void 정상_어택_테스트() {
        Board board = new Board();
        board.set(new Point(2, 2), new Pawn(ChessTeam.WHITE));
        board.set(new Point(3, 3), new Pawn(ChessTeam.BLACK));
        board.play(new Point(2, 2), new Point(3, 3));
        assertEquals(new Pawn(ChessTeam.WHITE), board.get(new Point(3, 3)));
        assertEquals(new Empty(), board.get(new Point(2, 2)));
    }

    @Test
    void 어택_불가_테스트() {
        Board board = new Board();
        System.out.println(board.get(new Point(2, 2)));
        board.set(new Point(2, 2), new Pawn(ChessTeam.WHITE));
        board.set(new Point(3, 3), new Pawn(ChessTeam.WHITE));

        assertThrows(IllegalArgumentException.class, () -> board.play(new Point(2, 2), new Point(3, 3)));
    }

    @Test
    void ROOK_플레이_테스트() {
        Board board = new Board();
        board.set(new Point(2, 2), new Rook(ChessTeam.WHITE));
        board.play(new Point(2, 2), new Point(8, 2));
        assertEquals(new Rook(ChessTeam.WHITE), board.get(new Point(8, 2)));
    }

    @Test
    void ROOK_플레이_테스트2() {
        Board board = new Board();
        board.set(new Point(2, 2), new Rook(ChessTeam.WHITE));
        board.set(new Point(8, 2), new Pawn(ChessTeam.BLACK));
        board.play(new Point(2, 2), new Point(8, 2));
        assertEquals(new Rook(ChessTeam.WHITE), board.get(new Point(8, 2)));
    }
}