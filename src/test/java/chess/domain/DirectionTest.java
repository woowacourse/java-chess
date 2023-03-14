package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DirectionTest {

    @DisplayName("상하 대칭 할 수 있다.")
    @ParameterizedTest(name = "{0}을 상하 대칭하면 {1}이다.")
    @CsvSource({"UP,DOWN", "DOWN,UP", "LEFT,LEFT", "RIGHT,RIGHT"})
    void flipHorizontal(Direction oldDirection, Direction newDirection) {
        assertThat(oldDirection.flipHorizontal())
            .isEqualTo(newDirection);
    }
    
    @DisplayName("좌우 대칭 할 수 있다.")
    @ParameterizedTest(name = "{0}을 좌우 대칭하면 {1}이다.")
    @CsvSource({"UP,UP", "DOWN,DOWN", "LEFT,RIGHT", "RIGHT,LEFT"})
    void flipVertical(Direction oldDirection, Direction newDirection) {
        assertThat(oldDirection.flipVertical())
            .isEqualTo(newDirection);
    }
}
