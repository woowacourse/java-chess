package chess;

import chess.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new BoardFactory());
    }

    @Test
    void test_moveEmptyFrom() {
        final Position emptyPosition = new Position(4, 4);
        final Position to = new Position(1, 1);

        assertThatThrownBy(() -> board.move(emptyPosition, to, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발점에 말이 없습니다.");
    }

    @Test
    void test_movePawn() {
        final Position from = new Position(1, 2);
        final Position to = new Position(1, 4);

        assertTrue(board.board().containsKey(from));
        assertFalse(board.board().containsKey(to));

        board.move(from, to, Color.WHITE);

        assertFalse(board.board().containsKey(from));
        assertTrue(board.board().containsKey(to));
    }

    @Test
    @DisplayName("move() : 이동할 때 중간에 기물이 존재한다면 IllegalStateException이 발생합니다.")
    void test_move_validateObstacle_exception() throws Exception {
        //given
        final Position from = new Position(1, 1);
        final Position to = new Position(1, 3);

        //when & then
        assertThatThrownBy(() -> board.move(from, to, Color.WHITE))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("중간에 다른 기물이 존재합니다.");
    }
}
