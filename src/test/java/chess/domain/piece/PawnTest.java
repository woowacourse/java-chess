package chess.domain.piece;

import chess.domain.Position;
import chess.domain.player.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    private final Position currentPosition = Position.of(2, 'g');
    private final Pawn pawn = new Pawn(currentPosition);

    @Test
    @DisplayName("앞이 아닌 방향으로 이동할 경우, 예외를 발생시킨다.")
    void moveDirectionException() {
        final int currentRank = 2;
        final Position nextPosition = Position.of(currentRank, 'd');

        assertThatThrownBy(() -> pawn.move(currentPosition, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 캡쳐할 수 있는 상대말이 없는 경우, 앞으로만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("처음 위치에서 1칸 또는 2칸 이동이 아닌 경우 예외를 발생시킨다.")
    void moveFirstTurnException() {
        final int currentRank = 2;
        final Position nextPosition = Position.of(currentRank + 3, 'g');

        assertThatThrownBy(() -> pawn.move(currentPosition, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 첫번째 턴에는 1칸 또는 2칸만 이동할 수 있습니다.");
    }

    @ParameterizedTest
    @DisplayName("처음 위치에서 1칸 또는 2칸 이동 가능하다.")
    @CsvSource({"3, 'g'", "4, 'g'"})
    void moveFirstTurn(final int rank, final char file) {
        final Position expected = Position.of(rank, file);

        final Position actual = pawn.move(currentPosition, Position.of(rank, file), Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("대각선에 존재하는 말을 잡는다.")
    @CsvSource({"3, 'f'", "3, 'h'"})
    void capture(final int rank, final char file) {
        final Position expected = Position.of(rank, file);

        final Position actual = pawn.capture(currentPosition, Position.of(rank, file), Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("캡쳐 시 대각선 이동 방향이 아닌 경우, 예외를 발생시킨다.")
    void moveDiagonalDirectionException() {
        final int currentRank = 2;
        final Position nextPosition = Position.of(currentRank + 1, 'g');

        assertThatThrownBy(() -> pawn.capture(currentPosition, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 캡쳐 목적으로는 대각선으로만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("캡쳐 시 대각선 이동으로 1칸이 아닌 경우, 예외를 발생시킨다.")
    void moveDiagonalDistanceException() {
        final int currentRank = 2;
        final Position nextPosition = Position.of(currentRank + 2, 'e');

        assertThatThrownBy(() -> pawn.capture(currentPosition, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 대각선으로 1칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("앞으로 한 칸 이동한다.")
    void move() {
        final Position expected = Position.of(3, 'g');

        final Position actual = pawn.move(currentPosition, expected, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("첫 번째 턴이 아니고, 한 칸 이동이 아닌 경우, 예외를 발생시킨다.")
    void moveCountException() {
        final Position currentPositionNotFirstTurn = Position.of(3, 'g');
        final Pawn pawnNotFirstTurn = new Pawn(currentPositionNotFirstTurn);
        final Position nextPosition = Position.of(5, 'g');

        assertThatThrownBy(() -> pawnNotFirstTurn.move(currentPositionNotFirstTurn, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 앞으로 1칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("위치가 주어질 때, 해당 위치에 자신이 존재하는지 확인한다.")
    void checkMyPosition() {
        final Position checkingPosition = Position.of(2, 'g');

        assertThat(pawn.exist(checkingPosition)).isTrue();
    }
}
