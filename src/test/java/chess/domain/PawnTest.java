package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    private final Position currentPosition = new Position(2, 'g');
    private final Pawn pawn = new Pawn(currentPosition);

    @Test
    @DisplayName("앞이 아닌 방향으로 이동할 경우, 예외를 발생시킨다.")
    void moveDirectionException() {
        final int currentRank = 2;
        final Position nextPosition = new Position(currentRank, 'd');

        assertThatThrownBy(() -> pawn.move(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("전진이 아닌 다른 방향으로 이동했습니다.");
    }

    @Test
    @DisplayName("첫번째 턴에서 1칸 또는 2칸 이동이 아닌 경우 예외를 발생시킨다.")
    void moveFirstTurnException() {
        final int currentRank = 2;
        final Position nextPosition = new Position(currentRank + 3, 'g');

        assertThatThrownBy(() -> pawn.move(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 첫번째 턴에는 1칸 또는 2칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("첫번째 턴에선 1칸 또는 2칸 이동 가능하다.")
    void moveFirstTurn() {
        final Position expected = new Position(3, 'g');

        final Position actual = pawn.move(currentPosition, new Position(3, 'g'));

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @DisplayName("대각선에 존재하는 말을 잡는다.")
    @CsvSource({"3, 'f'", "3, 'h'"})
    void capture(final int rank, final char file) {
        final Position expected = new Position(rank, file);

        final Position actual = pawn.capture(currentPosition, new Position(rank, file));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("대각선 이동으로 1칸이 주어지지 않은 경우, 예외를 발생시킨다.")
    void moveDiagonalException() {
        final int currentRank = 2;
        final Position nextPosition = new Position(currentRank + 2, 'e');

        assertThatThrownBy(() -> pawn.capture(currentPosition, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 상대 말이 존재할 경우만 대각선으로 1칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("앞으로 한 칸 이동한다.")
    void move() {
        final Position expected = new Position(3, 'g');

        final Position actual = pawn.move(currentPosition, expected);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("첫 번째 턴이 아니고, 한 칸 이동이 아닌 경우, 예외를 발생시킨다.")
    void moveCountException() {
        final Position currentPositionNotFirstTurn = new Position(3, 'g');
        final Pawn pawnNotFirstTurn = new Pawn(currentPositionNotFirstTurn);
        final Position nextPosition = new Position(5, 'g');

        assertThatThrownBy(() -> pawnNotFirstTurn.move(currentPositionNotFirstTurn, nextPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 앞으로 1칸만 이동할 수 있습니다.");
    }
}
