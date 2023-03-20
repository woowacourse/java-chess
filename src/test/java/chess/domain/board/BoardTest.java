package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.piece.Color;
import chess.domain.position.Position;
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

        assertThatThrownBy(() -> board.move(emptyPosition, new Position(1, 1), Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발점에 말이 없습니다.");
    }

    @Test
    void test_movePawn() {
        Position from = new Position(1, 2);
        Position to = new Position(1, 4);

        assertTrue(board.getBoard().containsKey(from));
        assertFalse(board.getBoard().containsKey(to));

        board.move(from, to, Color.WHITE);

        assertFalse(board.getBoard().containsKey(from));
        assertTrue(board.getBoard().containsKey(to));
    }
}
