package chess.domain.position;

import chess.exception.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {
    @DisplayName("Position의 equals 테스트")
    @ParameterizedTest
    @EnumSource(File.class)
    void getPositionTest(File file) {
        for (Rank rank : Rank.values()) {
            Position position = Position.of(file, rank);

            assertThat(position).isEqualTo(Position.of(file, rank));
        }
    }

    @DisplayName("위치 형식(File + Rank의 두 글자)에 맞지 않는 위치 값이 입력되면 예외를 발생시킨다.")
    @ParameterizedTest
    @CsvSource({"aaa", "111", "a9", "i1"})
    void throwExceptionWhenWrongFormatArgument(String input) {
        assertThatThrownBy(() -> Position.of(input))
                .isInstanceOf(DomainException.class)
                .hasMessage("위치 형식에 맞는 입력이 아닙니다.");
    }

    @DisplayName("x좌표 상의 차이를 구한다")
    @ParameterizedTest
    @CsvSource({"a1, b1, 1", "a1, h1, 7", "b1, a1, -1", "h1, a1, -7", "a1, b2, 1", "b2, a1, -1"})
    void calculateXDegreeTest(String from, String to, int expected) {
        assertThat(Position.of(from).calculateXDegree(Position.of(to))).isEqualTo(expected);
    }

    @DisplayName("y좌표 상의 차이를 구한다")
    @ParameterizedTest
    @CsvSource({"a1, a2, 1", "a1, a8, 7", "a2, a1, -1", "a8, a1, -7", "a1, b2, 1", "b2, a1, -1"})
    void calculateYDegreeTest(String from, String to, int expected) {
        assertThat(Position.of(from).calculateYDegree(Position.of(to))).isEqualTo(expected);
    }

    @DisplayName("Direction을 기준으로 다음 Position을 구한다")
    @ParameterizedTest
    @MethodSource
    void getNextPositionTest(Position source, Direction direction, Position target) {
        assertThat(source.getNextPosition(direction)).isEqualTo(target);
    }

    static Stream<Arguments> getNextPositionTest() {
        return Stream.of(
                Arguments.of(Position.of("a1"), Direction.NORTH, Position.of("a2")),
                Arguments.of(Position.of("a2"), Direction.SOUTH, Position.of("a1")),
                Arguments.of(Position.of("a1"), Direction.EAST, Position.of("b1")),
                Arguments.of(Position.of("b1"), Direction.WEST, Position.of("a1")),
                Arguments.of(Position.of("a1"), Direction.NORTHEAST, Position.of("b2")),
                Arguments.of(Position.of("b1"), Direction.NORTHWEST, Position.of("a2")),
                Arguments.of(Position.of("a2"), Direction.SOUTHEAST, Position.of("b1")),
                Arguments.of(Position.of("b2"), Direction.SOUTHWEST, Position.of("a1")),

                Arguments.of(Position.of("a1"), Direction.EEN, Position.of("c2")),
                Arguments.of(Position.of("a2"), Direction.EES, Position.of("c1")),
                Arguments.of(Position.of("c1"), Direction.WWN, Position.of("a2")),
                Arguments.of(Position.of("c2"), Direction.WWS, Position.of("a1")),
                Arguments.of(Position.of("a1"), Direction.NNE, Position.of("b3")),
                Arguments.of(Position.of("b1"), Direction.NNW, Position.of("a3")),
                Arguments.of(Position.of("a3"), Direction.SSE, Position.of("b1")),
                Arguments.of(Position.of("b3"), Direction.SSW, Position.of("a1"))
        );
    }
}