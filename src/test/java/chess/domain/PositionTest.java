package chess.domain;

import chess.domain.player.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class PositionTest {

    private static Stream<Arguments> provideInvalidPosition() {
        return Stream.of(
                Arguments.of(0, 'a'),
                Arguments.of(4, 'i'),
                Arguments.of(9, 'a'),
                Arguments.of(8, 'I')
        );
    }

    private static Stream<Arguments> provideDestinationPosition() {
        return Stream.of(
                Arguments.of(Position.of(1, 'd'),
                        List.of(Position.of(3, 'd'), Position.of(2, 'd')),
                        "남쪽"),
                Arguments.of(Position.of(7, 'd'),
                        List.of(Position.of(5, 'd'), Position.of(6, 'd')),
                        "북쪽"),
                Arguments.of(Position.of(4, 'a'),
                        List.of(Position.of(4, 'c'), Position.of(4, 'b')),
                        "서쪽"),
                Arguments.of(Position.of(4, 'g'),
                        List.of(Position.of(4, 'e'), Position.of(4, 'f')),
                        "동쪽"),
                Arguments.of(Position.of(1, 'a'),
                        List.of(Position.of(3, 'c'), Position.of(2, 'b')),
                        "남서대각선"),
                Arguments.of(Position.of(1, 'g'),
                        List.of(Position.of(3, 'e'), Position.of(2, 'f')),
                        "남동대각선"),
                Arguments.of(Position.of(7, 'a'),
                        List.of(Position.of(5, 'c'), Position.of(6, 'b')),
                        "북서대각선"),
                Arguments.of(Position.of(7, 'g'),
                        List.of(Position.of(5, 'e'), Position.of(6, 'f')),
                        "북동대각선")
        );
    }

    @Test
    @DisplayName("범위가 정상인 경우, Position을 생성한다.")
    void createPosition() {
        assertThatCode(() -> Position.of(2, 'B'))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("범위를 벗어난 경우, 예외를 발생시킨다.")
    @MethodSource("provideInvalidPosition")
    void createPositionException(final int rank, final char file) {
        assertThatThrownBy(() -> Position.of(rank, file))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 범위입니다.");
    }

    @Test
    @DisplayName("화이트 기준 전진으로 이동하는지 확인한다.")
    void checkWhiteMoveForward() {
        final Position currentPosition = Position.of(2, 'b');
        final Position destinationPosition = Position.of(3, 'b');

        final boolean actual = currentPosition.isMoveForward(destinationPosition, Team.WHITE);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("블랙 기준 전진으로 이동하는지 확인한다.")
    void checkBlackMoveForward() {
        final Position currentPosition = Position.of(7, 'b');
        final Position destinationPosition = Position.of(5, 'b');

        final boolean actual = currentPosition.isMoveForward(destinationPosition, Team.BLACK);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("대각선으로 이동하는지 확인한다.")
    void checkMoveDiagonal() {
        final Position currentPosition = Position.of(2, 'a');
        final Position destinationPosition = Position.of(4, 'c');

        final boolean actual = currentPosition.isMoveDiagonal(destinationPosition);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("화이트 기준 앞 방향으로 대각선으로 이동하는지 확인한다.")
    void checkWhiteMoveForwardDiagonal() {
        final Position currentPosition = Position.of(2, 'b');
        final Position destinationPosition = Position.of(3, 'a');

        final boolean actual = currentPosition.isMoveDiagonalForward(destinationPosition, Team.WHITE);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("블랙 기준 앞 방향으로 대각선으로 이동하는지 확인한다.")
    void checkBlackMoveForwardDiagonal() {
        final Position currentPosition = Position.of(8, 'a');
        final Position destinationPosition = Position.of(7, 'b');

        final boolean actual = currentPosition.isMoveDiagonalForward(destinationPosition, Team.BLACK);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("목적지까지의 거리를 구한다.")
    void calculateDistance() {
        final Position currentPosition = Position.of(2, 'a');
        final Position destinationPosition = Position.of(4, 'c');
        final int expected = 4;

        final int actual = currentPosition.calculateDistance(destinationPosition);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("provideDestinationPosition")
    @DisplayName("목적지까지 존재하는 중간 위치를 모두 구한다.")
    void findAllBetweenCurrentAndDestination(final Position position, final List<Position> expected, final String name) {
        final Position currentPosition = Position.of(4, 'd');

        final List<Position> actual = currentPosition.findAllBetweenPosition(position);

        assertThat(actual).isEqualTo(expected);
    }
}
