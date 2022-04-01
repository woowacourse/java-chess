package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import chess.domain.player.Team;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class QueenTest {

    private final Position currentPosition = new Position(3, 'c');
    private final Queen queen = new Queen(currentPosition);

    @ParameterizedTest
    @DisplayName("상하좌우, 대각선 중 한 방향으로 칸수 제한없이 이동 가능하다.")
    @MethodSource("provideDestinationPosition")
    void move(final Position expected) {
        final Position actual = queen.move(currentPosition, expected, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideDestinationPosition() {
        return Stream.of(Arguments.of(
                new Position(6, 'c'),
                new Position(1, 'c'),
                new Position(3, 'a'),
                new Position(3, 'e'),
                new Position(5, 'a'),
                new Position(5, 'e'),
                new Position(1, 'a'),
                new Position(1, 'e')
        ));
    }

    @Test
    @DisplayName("퀸이 가능한 이동이 아닐 경우, 예외를 발생시킨다.")
    void moveException() {
        final Position nextDiagonalPosition = new Position(5, 'b');

        assertThatThrownBy(() -> queen.move(currentPosition, nextDiagonalPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("퀸은 상하좌우, 대각선 중 한 방향으로 이동해야 합니다.");
    }

    @Test
    @DisplayName("위치가 주어질 때, 해당 위치에 자신이 존재하는지 확인한다.")
    void checkMyPosition() {
        final Position checkingPosition = new Position(3, 'c');

        assertThat(queen.exist(checkingPosition)).isTrue();
    }
}
