package chess.domain.pieceInfo;

import java.util.List;
import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class PositionTest {

    private static Stream<Arguments> getInternalPositionsParameters() {
        return Stream.of(
                Arguments.of(Position.of("d7"), List.of(Position.of("d5"), Position.of("d6"))),
                Arguments.of(Position.of("g7"), List.of(Position.of("e5"), Position.of("f6"))),
                Arguments.of(Position.of("g4"), List.of(Position.of("e4"), Position.of("f4"))),
                Arguments.of(Position.of("g1"), List.of(Position.of("e3"), Position.of("f2"))),
                Arguments.of(Position.of("d1"), List.of(Position.of("d3"), Position.of("d2"))),
                Arguments.of(Position.of("a1"), List.of(Position.of("c3"), Position.of("b2"))),
                Arguments.of(Position.of("a4"), List.of(Position.of("c4"), Position.of("b4"))),
                Arguments.of(Position.of("a7"), List.of(Position.of("c5"), Position.of("b6")))
        );
    }

    @DisplayName("x, y 좌표로 Position 객체가 생성된다.")
    @Test
    void createPositionByCoordinateTest() {
        Position position = Position.of("a1");

        Assertions.assertThat(position).isNotNull();
    }

    @DisplayName("보드의 범위를 벗어난 좌표이면 예외를 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"a9", "i1", "00", "1a", "aa"})
    void validateRangeTest(String position) {
        Assertions.assertThatThrownBy(() -> Position.of(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보드의 범위를 벗어난 좌표입니다.");
    }

    @DisplayName("서로 다른 좌표간의 차이를 반환한다.")
    @Test
    void calculatePositionDifferenceTest() {
        Position position = Position.of("a1");
        Position otherPosition = Position.of("c5");

        PositionDifference actualPositionDifference = position.calculateDifference(otherPosition);
        PositionDifference expectedPositionDifference = new PositionDifference(-2, -4);

        Assertions.assertThat(actualPositionDifference).isEqualTo(expectedPositionDifference);
    }
    
    @DisplayName("좌표 사이에 있는 좌표들을 반환한다.")
    @ParameterizedTest
    @MethodSource("getInternalPositionsParameters")
    void getInternalPositionsTest(Position otherPosition, List<Position> expectedInternalPositions) {
        Position position = Position.of("d4");
        List<Position> actualInternalPositions = position.getInternalPositions(otherPosition);

        Assertions.assertThat(actualInternalPositions).isEqualTo(expectedInternalPositions);
    }

    @DisplayName("해당 좌표와 같은 세로줄에 있는 좌표들을 반환한다.")
    @Test
    void getVerticalInternalPositionsTest() {
        Position position = Position.of("d4");

        List<Position> expectedOtherPositions = position.getVerticalInternalPositions();
        List<Position> actualOtherPositions = List.of(
                Position.of("d1"),
                Position.of("d2"),
                Position.of("d3"),
                Position.of("d5"),
                Position.of("d6"),
                Position.of("d7"),
                Position.of("d8")
        );

        Assertions.assertThat(expectedOtherPositions).containsAll(actualOtherPositions);
    }
}
