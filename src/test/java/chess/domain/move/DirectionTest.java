package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.AbstractTestFixture;

class DirectionTest extends AbstractTestFixture {

    @DisplayName("수평 축을 기준으로 뒤집을 수 있다.")
    @ParameterizedTest(name = "{0}을 상하 대칭하면 {1}이다.")
    @CsvSource({"UP,DOWN", "DOWN,UP", "LEFT,LEFT", "RIGHT,RIGHT"})
    void flipHorizontal(Direction oldDirection, Direction newDirection) {
        assertThat(oldDirection.flipOver(Axis.HORIZON))
                .isEqualTo(newDirection);
    }

    @DisplayName("수직 축을 기준으로 뒤집을 수 있다.")
    @ParameterizedTest(name = "{0}을 좌우 대칭하면 {1}이다.")
    @CsvSource({"UP,UP", "DOWN,DOWN", "LEFT,RIGHT", "RIGHT,LEFT"})
    void flipVertical(Direction oldDirection, Direction newDirection) {
        assertThat(oldDirection.flipOver(Axis.VERTICAL))
                .isEqualTo(newDirection);
    }
}
