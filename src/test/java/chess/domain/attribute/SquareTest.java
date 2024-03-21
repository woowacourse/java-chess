package chess.domain.attribute;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.chessboard.attribute.Direction;

class SquareTest {

    private static Stream<Arguments> stringConstructor() {
        return Stream.of(
                Arguments.of("a1", Square.of(File.A, Rank.ONE)),
                Arguments.of("b2", Square.of(File.B, Rank.TWO)),
                Arguments.of("c3", Square.of(File.C, Rank.THREE)),
                Arguments.of("d4", Square.of(File.D, Rank.FOUR)),
                Arguments.of("e5", Square.of(File.E, Rank.FIVE)),
                Arguments.of("f6", Square.of(File.F, Rank.SIX)),
                Arguments.of("g7", Square.of(File.G, Rank.SEVEN)),
                Arguments.of("h8", Square.of(File.H, Rank.EIGHT))
        );
    }

    @DisplayName("좌표를 입력받아 체스판의 칸을 생성한다.")
    @MethodSource
    @ParameterizedTest
    void stringConstructor(String input, Square expected) {
        assertThat(Square.of(input)).isEqualTo(expected);
    }

    private static Stream<Arguments> move() {
        return Stream.of(
                Arguments.of(Direction.UP, Square.of(File.D, Rank.FIVE)),
                Arguments.of(Direction.UP_LEFT, Square.of(File.C, Rank.FIVE)),
                Arguments.of(Direction.UP_RIGHT, Square.of(File.E, Rank.FIVE)),
                Arguments.of(Direction.DOWN, Square.of(File.D, Rank.THREE)),
                Arguments.of(Direction.DOWN_LEFT, Square.of(File.C, Rank.THREE)),
                Arguments.of(Direction.DOWN_RIGHT, Square.of(File.E, Rank.THREE)),
                Arguments.of(Direction.LEFT, Square.of(File.C, Rank.FOUR)),
                Arguments.of(Direction.RIGHT, Square.of(File.E, Rank.FOUR))
        );
    }

    @DisplayName("현재위치에서 방향을 입력받아 다음 위치를 반환한다.")
    @ParameterizedTest
    @MethodSource
    void move(Direction direction, Square next) {
        Square current = Square.of(File.D, Rank.FOUR);
        Square actual = current.move(direction).get();
        assertThat(actual).isEqualTo(next);
    }

    private static Stream<Arguments> moveException() {
        return Stream.of(
                Arguments.of(Square.of(File.D, Rank.EIGHT), Direction.UP),
                Arguments.of(Square.of(File.A, Rank.EIGHT), Direction.UP_LEFT),
                Arguments.of(Square.of(File.H, Rank.EIGHT), Direction.UP_RIGHT),
                Arguments.of(Square.of(File.D, Rank.ONE), Direction.DOWN),
                Arguments.of(Square.of(File.A, Rank.ONE), Direction.DOWN_LEFT),
                Arguments.of(Square.of(File.H, Rank.ONE), Direction.DOWN_RIGHT),
                Arguments.of(Square.of(File.A, Rank.FOUR), Direction.LEFT),
                Arguments.of(Square.of(File.H, Rank.FOUR), Direction.RIGHT)
        );
    }

    @DisplayName("이동할 수 없는 칸으로 이동하면 빈 값을 반환한다.")
    @ParameterizedTest
    @MethodSource
    void moveException(Square current, Direction direction) {
        Optional<Square> square = current.move(direction);
        assertThat(square).isEmpty();
    }
}