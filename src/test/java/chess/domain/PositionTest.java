package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.player.Team;
import java.util.List;
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
    @DisplayName("화이트 기준 전진으로 이동하는지 확인한다.")
    void checkWhiteMoveForward() {
        final Position currentPosition = new Position(2, 'b');
        final Position destinationPosition = new Position(3, 'b');

        final boolean actual = currentPosition.isMoveForward(destinationPosition, Team.WHITE);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("블랙 기준 전진으로 이동하는지 확인한다.")
    void checkBlackMoveForward() {
        final Position currentPosition = new Position(7, 'b');
        final Position destinationPosition = new Position(5, 'b');

        final boolean actual = currentPosition.isMoveForward(destinationPosition, Team.BLACK);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("대각선으로 이동하는지 확인한다.")
    void checkMoveDiagonal() {
        final Position currentPosition = new Position(2, 'a');
        final Position destinationPosition = new Position(4, 'c');

        final boolean actual = currentPosition.isMoveDiagonal(destinationPosition);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("목적지까지의 거리를 구한다.")
    void calculateDistance() {
        final Position currentPosition = new Position(2, 'a');
        final Position destinationPosition = new Position(4, 'c');
        final int expected = 4;

        final int actual = currentPosition.calculateDistance(destinationPosition);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideDestinationPosition")
    @DisplayName("목적지까지 존재하는 중간 위치를 모두 구한다.")
    void findAllBetweenCurrentAndDestination(final Position position, final List<Position> expected, final String name) {
        final Position currentPosition = new Position(4, 'd');

        final List<Position> actual = currentPosition.findAllBetweenPosition(position);

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideDestinationPosition() {
        return Stream.of(
                Arguments.of(new Position(1, 'd'),
                        List.of(new Position(2, 'd'), new Position(3, 'd')),
                        "상하좌우"),
                Arguments.of(new Position(1, 'a'),
                        List.of(new Position(2, 'b'), new Position(3, 'c')),
                        "대각선")
        );
    }
}
