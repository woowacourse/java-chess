package chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PositionTest {

    @Test
    @DisplayName("(1,1) 에서 (8,8)의 좌표를 생성할 수 있다.")
    void createPosition() {
        Assertions.assertThatCode(() -> new Position(1, 1))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    @DisplayName("(1,1) 에서 (8,8)의 범위를 벗어난 열의 좌표를 생성할 수 있다.")
    void createPositionByInvalidateColumnRange(int x) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new Position(x, 1))
                .withMessage("올바르지 않은 열입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    @DisplayName("(1,1) 에서 (8,8)의 범위를 벗어난 행의 좌표를 생성할 수 있다.")
    void createPositionByInvalidateRowRange(int y) {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new Position(1, y))
                .withMessage("올바르지 않은 행입니다.");
    }
}