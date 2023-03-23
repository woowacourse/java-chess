package chess.domain.board;

import chess.domain.piece.property.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    private static final Position A1 = new Position(File.A, Rank.ONE);
    private static final Position A2 = new Position(File.A, Rank.TWO);
    private static final Position A3 = new Position(File.A, Rank.THREE);
    private static final Position A4 = new Position(File.A, Rank.FOUR);
    private static final Position A6 = new Position(File.A, Rank.SIX);
    private static final Position A7 = new Position(File.A, Rank.SEVEN);
    private final Board board = Board.initializeBoard();

    @Test
    @DisplayName("자신의 기물이 아닌 것을 움직일 수 없다")
    void cannotMoveOpponent() {
        assertThatThrownBy(() -> board.confirmMove(A7, A6, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("움직일 수 있는");
    }

    @Test
    @DisplayName("빈 칸을 움직일 수 없다")
    void cannotMoveEmpty() {
        assertThatThrownBy(() -> board.confirmMove(A3, A4, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비어있는");
    }

    @Test
    @DisplayName("자신의 기물을 목표로 움직일 수 없다")
    void cannotMoveToOwnPiece() {
        assertThatThrownBy(() -> board.confirmMove(A1, A2, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("자신의 기물이");
    }

    @Test
    @DisplayName("유효한 움직임이 아니면 예외가 발생한다")
    void illegalMoveTest() {
        assertThatThrownBy(() -> board.confirmMove(A2, A7, Color.WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
