package chess.domain.piece;

import chess.domain.Position;
import chess.domain.player.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RookTest {

    private final Position currentPosition = Position.of(3, 'c');
    private final Rook rook = new Rook(currentPosition);

    @ParameterizedTest(name = "{2} 방향으로 이동")
    @DisplayName("상하좌우 중 한 방향으로 칸수 제한없이 이동 가능하다.")
    @CsvSource({"7, 'c', '상'", "1, 'c', '하'", "3, 'a', '좌'", "3, 'h', '우'"})
    void move(final int rank, final char file, final char direction) {
        final Position expected = Position.of(rank, file);

        final Position actual = rook.move(currentPosition, expected, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("상하좌우 방향이 아닌 이동일 경우, 예외를 발생시킨다.")
    void moveException() {
        final Position nextDiagonalPosition = Position.of(6, 'f');

        assertThatThrownBy(() -> rook.move(currentPosition, nextDiagonalPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("룩은 상하좌우 중 한 방향으로만 이동해야 합니다.");
    }

    @Test
    @DisplayName("위치가 주어질 때, 해당 위치에 자신이 존재하는지 확인한다.")
    void checkMyPosition() {
        final Position checkingPosition = Position.of(3, 'c');

        assertThat(rook.exist(checkingPosition)).isTrue();
    }
}
