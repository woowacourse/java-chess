package chess;

import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.A2;
import static chess.fixture.PositionFixture.A4;
import static chess.fixture.PositionFixture.D4;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new BoardFactory().createInitialBoard();
    }

    @DisplayName("체스판은 말을 성공적으로 이동시킬 수 있다.")
    @Nested
    class moveTest {
        @DisplayName("빈 출발점을 선택하는 경우 예외 처리한다.")
        @Test
        void test_moveEmptyFrom() {
            assertThatThrownBy(() -> board.move(D4, A1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("출발점에 말이 없습니다.");
        }

        @DisplayName("체스판은 말을 이동할 수 있다.")
        @Test
        void test_movePawn() {
            Position from = A2;
            Position to = A4;

            assertTrue(board.board().containsKey(from));
            assertFalse(board.board().containsKey(to));

            board.move(from, to);

            assertFalse(board.board().containsKey(from));
            assertTrue(board.board().containsKey(to));
        }
    }
}
