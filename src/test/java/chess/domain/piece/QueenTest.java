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

class QueenTest {

    private final Position currentPosition = Position.of(3, 'c');
    private final Queen queen = new Queen(currentPosition);

    static Stream<Arguments> provideDestinationPosition() {
        return Stream.of(Arguments.of(
                Position.of(6, 'c'),
                Position.of(1, 'c'),
                Position.of(3, 'a'),
                Position.of(3, 'e'),
                Position.of(5, 'a'),
                Position.of(5, 'e'),
                Position.of(1, 'a'),
                Position.of(1, 'e')
        ));
    }

    @ParameterizedTest
    @DisplayName("상하좌우, 대각선 중 한 방향으로 칸수 제한없이 이동 가능하다.")
    @MethodSource("provideDestinationPosition")
    void move(final Position expected) {
        final Position actual = queen.move(currentPosition, expected, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("퀸이 가능한 이동이 아닐 경우, 예외를 발생시킨다.")
    void moveException() {
        final Position nextDiagonalPosition = Position.of(5, 'b');

        assertThatThrownBy(() -> queen.move(currentPosition, nextDiagonalPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("퀸은 상하좌우, 대각선 중 한 방향으로 이동해야 합니다.");
    }

    @Test
    @DisplayName("위치가 주어질 때, 해당 위치에 자신이 존재하는지 확인한다.")
    void checkMyPosition() {
        final Position checkingPosition = Position.of(3, 'c');

        assertThat(queen.exist(checkingPosition)).isTrue();
    }
}
