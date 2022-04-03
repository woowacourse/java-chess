package chess.domain.piece;

import chess.domain.Position;
import chess.domain.player.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {

    private final Position currentPosition = Position.of(3, 'c');
    private final King king = new King(currentPosition);

    static Stream<Arguments> provideDestinationPosition() {
        return Stream.of(Arguments.of(
                Position.of(4, 'c'),
                Position.of(2, 'c'),
                Position.of(3, 'b'),
                Position.of(3, 'd'),
                Position.of(4, 'b'),
                Position.of(4, 'd'),
                Position.of(2, 'b'),
                Position.of(2, 'd')
        ));
    }

    @ParameterizedTest
    @DisplayName("상하좌우, 대각선 중 한 방향으로 1칸 이동 가능하다.")
    @MethodSource("provideDestinationPosition")
    void move(final Position expected) {
        final Position actual = king.move(currentPosition, expected, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("킹이 이동한 방향이 올바르지 않을 경우, 예외를 발생시킨다.")
    void moveDirectionException() {
        final Position nextPosition = Position.of(4, 'a');

        assertThatThrownBy(() -> king.move(currentPosition, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 상하좌우, 대각선 중 한 방향으로 이동해야 합니다.");
    }


    @Test
    @DisplayName("킹이 이동한 칸수가 1칸이 아닐 경우, 예외를 발생시킨다.")
    void moveDistanceException() {
        final Position nextPosition = Position.of(5, 'c');

        assertThatThrownBy(() -> king.move(currentPosition, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 1칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("위치가 주어질 때, 해당 위치에 자신이 존재하는지 확인한다.")
    void checkMyPosition() {
        final Position checkingPosition = Position.of(3, 'c');

        assertThat(king.exist(checkingPosition)).isTrue();
    }
}
