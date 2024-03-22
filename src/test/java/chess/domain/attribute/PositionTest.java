package chess.domain.attribute;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.chessboard.attribute.Direction;

class PositionTest {

    private static Stream<Arguments> stringConstructor() {
        return Stream.of(
                Arguments.of("a1", Position.of(File.A, Rank.ONE)),
                Arguments.of("b2", Position.of(File.B, Rank.TWO)),
                Arguments.of("c3", Position.of(File.C, Rank.THREE)),
                Arguments.of("d4", Position.of(File.D, Rank.FOUR)),
                Arguments.of("e5", Position.of(File.E, Rank.FIVE)),
                Arguments.of("f6", Position.of(File.F, Rank.SIX)),
                Arguments.of("g7", Position.of(File.G, Rank.SEVEN)),
                Arguments.of("h8", Position.of(File.H, Rank.EIGHT))
        );
    }

    @DisplayName("좌표를 입력받아 체스판의 칸을 생성한다.")
    @MethodSource
    @ParameterizedTest
    void stringConstructor(String input, Position expected) {
        assertThat(Position.of(input)).isEqualTo(expected);
    }

    private static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of(Direction.UP, Position.of(File.D, Rank.FIVE)),
                Arguments.of(Direction.UP_LEFT, Position.of(File.C, Rank.FIVE)),
                Arguments.of(Direction.UP_RIGHT, Position.of(File.E, Rank.FIVE)),
                Arguments.of(Direction.DOWN, Position.of(File.D, Rank.THREE)),
                Arguments.of(Direction.DOWN_LEFT, Position.of(File.C, Rank.THREE)),
                Arguments.of(Direction.DOWN_RIGHT, Position.of(File.E, Rank.THREE)),
                Arguments.of(Direction.LEFT, Position.of(File.C, Rank.FOUR)),
                Arguments.of(Direction.RIGHT, Position.of(File.E, Rank.FOUR))
        );
    }

    @DisplayName("현재위치에서 방향을 입력받아 다음 위치를 반환한다.")
    @ParameterizedTest
    @MethodSource
    void move(Direction direction, Position next) {
        Position current = Position.of(File.D, Rank.FOUR);
        Position actual = current.moveTo(direction).get();
        assertThat(actual).isEqualTo(next);
    }

    private static Stream<Arguments> moveException() {
        return Stream.of(
                Arguments.of(Position.of(File.D, Rank.EIGHT), Direction.UP),
                Arguments.of(Position.of(File.A, Rank.EIGHT), Direction.UP_LEFT),
                Arguments.of(Position.of(File.H, Rank.EIGHT), Direction.UP_RIGHT),
                Arguments.of(Position.of(File.D, Rank.ONE), Direction.DOWN),
                Arguments.of(Position.of(File.A, Rank.ONE), Direction.DOWN_LEFT),
                Arguments.of(Position.of(File.H, Rank.ONE), Direction.DOWN_RIGHT),
                Arguments.of(Position.of(File.A, Rank.FOUR), Direction.LEFT),
                Arguments.of(Position.of(File.H, Rank.FOUR), Direction.RIGHT)
        );
    }

    @DisplayName("이동할 수 없는 칸으로 이동하면 빈 값을 반환한다.")
    @ParameterizedTest
    @MethodSource
    void moveException(Position current, Direction direction) {
        Optional<Position> position = current.moveTo(direction);
        assertThat(position).isEmpty();
    }
}