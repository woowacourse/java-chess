package chess.domain.move;

import static chess.domain.move.Direction.DOWN;
import static chess.domain.move.Direction.LEFT;
import static chess.domain.move.Direction.RIGHT;
import static chess.domain.move.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class DirectionTest {

    @DisplayName("상하 대칭 할 수 있다.")
    @ParameterizedTest(name = "{0}을 상하 대칭하면 {1}이다.")
    @CsvSource({"UP,DOWN", "DOWN,UP", "LEFT,LEFT", "RIGHT,RIGHT"})
    void flipVertical(Direction oldDirection, Direction newDirection) {
        assertThat(oldDirection.flipVertical())
                .isEqualTo(newDirection);
    }

    @DisplayName("좌우 대칭 할 수 있다.")
    @ParameterizedTest(name = "{0}을 좌우 대칭하면 {1}이다.")
    @CsvSource({"UP,UP", "DOWN,DOWN", "LEFT,RIGHT", "RIGHT,LEFT"})
    void flipHorizontal(Direction oldDirection, Direction newDirection) {
        assertThat(oldDirection.flipHorizontal())
                .isEqualTo(newDirection);
    }

    @DisplayName("file 변화량, rank 변화량으로 방향 리스트를 만들 수 있다.")
    @ParameterizedTest(name = "{0},{1} = {2}")
    @MethodSource
    void createDirections_fromDeltaFileAndDeltaRank(int deltaFile, int deltaRank, List<Direction> expectedDirections) {

        assertThat(Direction.from(deltaFile, deltaRank)).containsAll(expectedDirections);
    }

    static Stream<Arguments> createDirections_fromDeltaFileAndDeltaRank() {
        return Stream.of(
                Arguments.of(3, 3, List.of(RIGHT, RIGHT, RIGHT, UP, UP, UP)),
                Arguments.of(3, -3, List.of(RIGHT, RIGHT, RIGHT, DOWN, DOWN, DOWN)),
                Arguments.of(-3, 3, List.of(LEFT, LEFT, LEFT, UP, UP, UP)),
                Arguments.of(-3, -3, List.of(LEFT, LEFT, LEFT, DOWN, DOWN, DOWN))
        );
    }
}
