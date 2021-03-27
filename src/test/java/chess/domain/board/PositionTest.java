package chess.domain.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PositionTest {

    @DisplayName("좌표 방향 계산 테스트 - LinearDirection")
    @ParameterizedTest
    @CsvSource(value = {"b2, b5, NORTH", "f6, f2, SOUTH", "d4, a4, WEST", "b7, e7, EAST"})
    void calculateLinearDirection(String source, String target, Direction direction) {
        Position testSource = Position.of(source);
        Position testTarget = Position.of(target);
        Direction calculateDirection = testSource.calculateDirection(testTarget);

        assertThat(calculateDirection).isEqualTo(direction);
    }

    @DisplayName("좌표 방향 계산 테스트 - DiagonalDirection")
    @ParameterizedTest
    @CsvSource(value = {"b2, e5, NORTHEAST", "f2, b6, NORTHWEST", "b5, e2, SOUTHEAST",
            "h6, f4, SOUTHWEST"})
    void calculateDiagonalDirection(String source, String target, Direction direction) {
        Position testSource = Position.of(source);
        Position testTarget = Position.of(target);
        Direction calculateDirection = testSource.calculateDirection(testTarget);

        assertThat(calculateDirection).isEqualTo(direction);
    }

    @DisplayName("좌표 방향 계산 테스트 - KnightDirection")
    @ParameterizedTest
    @CsvSource(value = {"b2, c4, NNE", "b2, a4, NNW", "c3, d1, SSE", "h6, g4, SSW", "c3, e4, EEN",
            "c3, e2, EES", "c3, a4, WWN", "c3, a2, WWS"})
    void calculateKnightDirection(String source, String target, Direction direction) {
        Position testSource = Position.of(source);
        Position testTarget = Position.of(target);
        Direction calculateDirection = testSource.calculateDirection(testTarget);

        assertThat(calculateDirection).isEqualTo(direction);
    }
}