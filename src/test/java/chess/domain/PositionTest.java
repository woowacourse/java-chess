package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PositionTest {

    @Test
    @DisplayName("범위가 정상인 경우, Position을 생성한다.")
    void createPosition() {
        assertThatCode(() -> new Position(2, 'B'))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("범위를 벗어난 경우, 예외를 발생시킨다.")
    @MethodSource("provideInvalidPosition")
    void createPositionException(final int rank, final char file) {
        assertThatThrownBy(() -> new Position(rank, file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 범위입니다.");
    }

    private static Stream<Arguments> provideInvalidPosition() {
        return Stream.of(
                Arguments.of(0, 'a'),
                Arguments.of(4, 'i'),
                Arguments.of(9, 'a'),
                Arguments.of(8, 'I')
        );
    }

    @Test
    @DisplayName("전진으로 몇 칸 이동하는지 확인한다.")
    void checkMoveForward() {
        final Position currentPosition = new Position(2, 'b');
        final Position destinationPosition = new Position(3, 'b');
        final int expected = 1;

        final int actual = currentPosition.countMoveForward(destinationPosition);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("대각선으로 몇 칸 이동하는지 확인한다.")
    void checkMoveDiagonal() {
        final Position currentPosition = new Position(2, 'a');
        final Position destinationPosition = new Position(4, 'c');
        final int expected = 2;

        final int actual = currentPosition.countMoveDiagonal(destinationPosition);

        assertThat(actual).isEqualTo(expected);
    }
}
