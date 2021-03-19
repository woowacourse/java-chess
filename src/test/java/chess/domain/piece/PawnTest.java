package chess.domain.piece;

import chess.domain.order.MoveOrder;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.piece.Fixture.board;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {
    @DisplayName("행마에 대한 검증 - 직선")
    @ParameterizedTest
    @CsvSource({"a2, a3, WHITE, true", "b7, b6, BLACK, true", "c2, c5, WHITE, false", "g7, g4, BLACK, false"})
    void canMove_StraightDirection(String from, String to, Color color) {
        Pawn pawn = new Pawn(color);
        MoveOrder moveOrder = board.createMoveOrder(Position.of(from), Position.of(to));
        assertThat(pawn.canMove(moveOrder)).isEqualTo(true);
    }

    @DisplayName("직선으로 3칸 이상 이동할 경우 예외")
    @ParameterizedTest
    @CsvSource({"c2, c5, WHITE", "g7, g4, BLACK"})
    void throwExceptionWhenMoveOverThreeSquares(String from, String to, Color color) {
        Pawn pawn = new Pawn(color);
        MoveOrder moveOrder = board.createMoveOrder(Position.of(from), Position.of(to));
        assertThatThrownBy(() -> pawn.canMove(moveOrder))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰이 움직일 수 있는 범위를 벗어났습니다.");
    }
    //TODO 이동이 구현 된 후 대각선 이동 테스트 구현

}