package chess.domain.piece;

import chess.domain.Position;
import chess.domain.player.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    private final Position currentPosition = Position.of(3, 'c');
    private final Bishop bishop = new Bishop(currentPosition);

    @ParameterizedTest(name = "{2} 방향으로 이동")
    @DisplayName("대각선 중 한 방향으로 칸수 제한없이 이동 가능하다.")
    @CsvSource({"5, 'a', \"북서\"", "5, 'e', \"북동\"", "1, 'a', \"남서\"", "1, 'e', \"남동\""})
    void move(final int rank, final char file, final String direction) {
        final Position expected = Position.of(rank, file);

        final Position actual = bishop.move(currentPosition, expected, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("대각선 방향이 아닌 이동일 경우, 예외를 발생시킨다.")
    void moveException() {
        final Position nextLinearPosition = Position.of(3, 'f');

        assertThatThrownBy(() -> bishop.move(currentPosition, nextLinearPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비숍은 대각선으로 이동해야 합니다.");
    }

    @Test
    @DisplayName("위치가 주어질 때, 해당 위치에 자신이 존재하는지 확인한다.")
    void checkMyPosition() {
        final Position checkingPosition = Position.of(3, 'c');

        assertThat(bishop.exist(checkingPosition)).isTrue();
    }
}
