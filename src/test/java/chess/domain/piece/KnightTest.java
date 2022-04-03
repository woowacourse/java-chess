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

class KnightTest {

    private final Position currentPosition = Position.of(3, 'c');
    private final Knight knight = new Knight(currentPosition);

    static Stream<Arguments> provideDestinationPosition() {
        return Stream.of(Arguments.of(
                Position.of(4, 'a'),
                Position.of(5, 'b'),
                Position.of(5, 'd'),
                Position.of(4, 'e'),
                Position.of(2, 'e'),
                Position.of(1, 'd'),
                Position.of(1, 'b'),
                Position.of(2, 'a')
        ));
    }

    @ParameterizedTest
    @DisplayName("상하좌우 방향으로 1칸 이동 후 대각선 방향으로 1칸 이동 가능하다.")
    @MethodSource("provideDestinationPosition")
    void move(final Position expected) {
        final Position actual = knight.move(currentPosition, expected, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("나이트가 가능한 이동이 아닐 경우, 예외를 발생시킨다.")
    void moveException() {
        final Position nextLinearPosition = Position.of(3, 'f');

        assertThatThrownBy(() -> knight.move(currentPosition, nextLinearPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이트는 상하좌우로 1칸 이동 후 대각선으로 1칸 이동해야 합니다.");
    }

    @Test
    @DisplayName("위치가 주어질 때, 해당 위치에 자신이 존재하는지 확인한다.")
    void checkMyPosition() {
        final Position checkingPosition = Position.of(3, 'c');

        assertThat(knight.exist(checkingPosition)).isTrue();
    }
}
