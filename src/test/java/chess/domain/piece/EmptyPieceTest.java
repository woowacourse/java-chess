package chess.domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EmptyPieceTest {

    @ParameterizedTest
    @CsvSource(value = {
            "LEFT", "RIGHT", "UP", "DOWN",
            "LEFT_UP", "LEFT_DOWN", "RIGHT_UP", "RIGHT_DOWN",
            "KNIGHT_LEFT_UP", "KNIGHT_LEFT_DOWN",
            "KNIGHT_RIGHT_UP", "KNIGHT_RIGHT_DOWN",
            "KNIGHT_UP_LEFT", "KNIGHT_UP_RIGHT",
            "KNIGHT_DOWN_LEFT", "KNIGHT_DOWN_RIGHT"
    })
    @DisplayName("이동할 수 없는 방향이면 false를 반환한다.")
    void canNotMove(Direction direction) {
        EmptyPiece emptyPiece = EmptyPiece.of();

        boolean result = emptyPiece.canMoveInTargetDirection(direction);

        Assertions.assertThat(result).isFalse();
    }

    @DisplayName("폰인지를 반환한다.")
    @Test
    void isPawn() {
        EmptyPiece emptyPiece = EmptyPiece.of();

        boolean isPawn = emptyPiece.isPawn();

        Assertions.assertThat(isPawn).isFalse();
    }
}
