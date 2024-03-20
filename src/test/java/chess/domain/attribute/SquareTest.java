package chess.domain.attribute;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.chessboard.attribute.Direction;

class SquareTest {

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
        assertThat(current.move(direction)).isEqualTo(next);
    }

    private static Stream<Arguments> moveException() {
        return Stream.of(
                Arguments.of(Square.of(File.D, Rank.EIGHT), Direction.UP, "랭크는 1~8 사이로 입력해주세요: 9"),
                Arguments.of(Square.of(File.A, Rank.EIGHT), Direction.UP_LEFT, "파일은 1~8 사이로 입력해주세요: 0"),
                Arguments.of(Square.of(File.H, Rank.EIGHT), Direction.UP_RIGHT, "파일은 1~8 사이로 입력해주세요: 9"),
                Arguments.of(Square.of(File.D, Rank.ONE), Direction.DOWN, "랭크는 1~8 사이로 입력해주세요: 0"),
                Arguments.of(Square.of(File.A, Rank.ONE), Direction.DOWN_LEFT, "파일은 1~8 사이로 입력해주세요: 0"),
                Arguments.of(Square.of(File.H, Rank.ONE), Direction.DOWN_RIGHT, "파일은 1~8 사이로 입력해주세요: 9"),
                Arguments.of(Square.of(File.A, Rank.FOUR), Direction.LEFT, "파일은 1~8 사이로 입력해주세요: 0"),
                Arguments.of(Square.of(File.H, Rank.FOUR), Direction.RIGHT, "파일은 1~8 사이로 입력해주세요: 9")
        );
    }

    @DisplayName("이동할 수 없는 칸으로 이동한 경우 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource
    void moveException(Square current, Direction direction, String message) {
        assertThatThrownBy(() -> current.move(direction))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(message);
    }
}