package chess.domain;

import chess.domain.pieces.Empty;
import chess.domain.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = BoardCreator.create();
    }

    @Test
    void 정상_이동_테스트() {
        board.play(Point.get(2, 2), Point.get(2, 3));
        assertEquals(new Pawn(ChessTeam.WHITE), board.get(Point.get(2, 3)));
    }

    @Test
    void 이동_불가_테스트() {
        assertThrows(IllegalArgumentException.class, () -> board.play(Point.get(2, 2), Point.get(2, 5)));
    }

    @Test
    void 이동_불가_테스트2() {
        board.play(Point.get(2, 2), Point.get(2, 3));
        assertThrows(IllegalArgumentException.class, () -> board.play(Point.get(3, 2), Point.get(2, 3)));
    }

    @Test
    void 이동_불가_테스트3() {
        assertThrows(IllegalArgumentException.class, () -> board.play(Point.get(2, 2), Point.get(3, 3)));
    }

    @Test
    void 정상_어택_테스트() {
        board.play(Point.get(2, 7), Point.get(2, 5));
        board.play(Point.get(2, 5), Point.get(2, 4));
        board.play(Point.get(2, 4), Point.get(2, 3));
        assertEquals(new Pawn(ChessTeam.BLACK), board.get(Point.get(2, 3)));
        board.play(Point.get(1, 2), Point.get(2, 3));
        assertEquals(new Pawn(ChessTeam.WHITE), board.get(Point.get(2, 3)));
        assertEquals(new Empty(), board.get(Point.get(1, 2)));
    }

    @Test
    void 어택_불가_테스트() {
        board.play(Point.get(2, 2), Point.get(2, 3));
        assertThrows(IllegalArgumentException.class, () -> board.play(Point.get(1, 2), Point.get(2, 3)));
    }

    @Test
    void Black왕이_죽었는지_테스트() {
        board.play(Point.get(3, 2), Point.get(3, 3), ChessTeam.WHITE);
        board.play(Point.get(4, 7), Point.get(4, 6), ChessTeam.BLACK);
        board.play(Point.get(4, 1), Point.get(1, 4), ChessTeam.WHITE);
        board.play(Point.get(2, 8), Point.get(1, 6), ChessTeam.BLACK);
        board.play(Point.get(1, 4), Point.get(5, 8), ChessTeam.WHITE);
        assertTrue(board.check(ChessTeam.BLACK));
    }

    @Test
    void Black왕이_죽었는지_테스트2() {
        board.play(Point.get(3, 2), Point.get(3, 3), ChessTeam.WHITE);
        assertFalse(board.check(ChessTeam.BLACK));
    }
}