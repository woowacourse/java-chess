package chess;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new BoardFactory());
    }

    @Test
    void test_moveEmptyFrom() {
        Position emptyPosition = new Position(4, 4);

        assertThatThrownBy(() -> board.move(emptyPosition, new Position(1, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발점에 말이 없습니다.");
    }

    @Test
    void test_movePawn() {
        Position from = new Position(1, 2);
        Position to = new Position(1, 4);

        assertTrue(board.board().containsKey(from));
        assertFalse(board.board().containsKey(to));

        board.move(from, to);

        assertFalse(board.board().containsKey(from));
        assertTrue(board.board().containsKey(to));
    }
}
