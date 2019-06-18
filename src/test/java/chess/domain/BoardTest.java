package chess.domain;

import chess.domain.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {

    @Test
    void 정상_이동_테스트() {
        Board board = new Board();
        System.out.println(board.get(new Point(2, 2)));
        board.set(new Point(2, 2), new Pawn());
        board.move(new Point(2, 2), new Point(2, 3));
        assertEquals("P", board.get(new Point(2, 3)).toString());
    }

    @Test
    void 이동_불가_테스트() {
        Board board = new Board();
        board.set(new Point(2, 2), new Pawn());
        assertThrows(IllegalArgumentException.class, () -> board.move(new Point(2, 2), new Point(2, 5)));
    }

    @Test
    void 이동_불가_테스트2() {
        Board board = new Board();
        board.set(new Point(2, 2), new Pawn());
        board.set(new Point(2, 3), new Pawn());
        assertThrows(IllegalArgumentException.class, () -> board.move(new Point(2, 2), new Point(2, 3)));
    }

    @Test
    void 이동_불가_테스트3() {
        Board board = new Board();
        board.set(new Point(2, 2), new Pawn());
        assertThrows(IllegalArgumentException.class, () -> board.move(new Point(2, 2), new Point(3, 3)));
    }
}