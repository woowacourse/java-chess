package chess;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.board.Board;
import chess.board.BoardFactory;
import chess.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new BoardFactory().createInitialBoard();
    }

    @DisplayName("빈 출발점을 선택하는 경우 예외 처리한다.")
    @Test
    void test_moveEmptyFrom() {
        Position emptyPosition = new Position(4, 4);

        assertThatThrownBy(() -> board.move(emptyPosition, new Position(1, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("출발점에 말이 없습니다.");
    }

    @DisplayName("체스판은 말을 이동할 수 있다.")
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
