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
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class PawnTest {

    private final Position currentPosition = new Position(2, 'g');
    private final Pawn pawn = new Pawn(currentPosition);

    @Test
    @DisplayName("앞으로 한 칸 이동한다.")
    void move() {
        final Position expected = new Position(3, 'g');

        final Position actual = pawn.move(currentPosition, expected, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("provideFirstTurnPosition")
    @DisplayName("첫번째 턴에선 1칸 또는 2칸 이동 가능하다.")
    void moveFirstTurn(final Position expected) {
        final Position actual = pawn.move(currentPosition, expected, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideFirstTurnPosition() {
        return Stream.of(Arguments.of(
                new Position(3, 'g'),
                new Position(4, 'g')
        ));
    }

    @ParameterizedTest
    @CsvSource({"3, 'f'", "3, 'h'"})
    @DisplayName("대각선에 존재하는 말을 잡는다.")
    void capture(final int rank, final char file) {
        final Position expected = new Position(rank, file);

        final Position actual = pawn.capture(currentPosition, new Position(rank, file), Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("앞이 아닌 방향으로 이동할 경우, 예외를 발생시킨다.")
    void moveDirectionException() {
        final int currentRank = 2;
        final Position nextPosition = new Position(currentRank, 'd');

        assertThatThrownBy(() -> pawn.move(currentPosition, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 방향이 앞이 아닙니다.");
    }

    @Test
    @DisplayName("첫번째 턴에서 1칸 또는 2칸 이동이 아닌 경우 예외를 발생시킨다.")
    void moveFirstTurnException() {
        final int currentRank = 2;
        final Position nextPosition = new Position(currentRank + 3, 'g');

        assertThatThrownBy(() -> pawn.move(currentPosition, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 첫번째 턴에는 1칸 또는 2칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("상대말을 잡을 때 대각선 이동이 아닐 경우, 예외를 발생시킨다.")
    void moveDiagonalException() {
        final Position nextPosition = new Position(2, 'e');

        assertThatThrownBy(() -> pawn.capture(currentPosition, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 방향이 대각선이 아닙니다.");
    }

    @Test
    @DisplayName("상대말을 잡을 때 대각선 이동 거리가 1칸이 아닌 경우, 예외를 발생시킨다.")
    void moveDiagonalDistanceException() {
        final int currentRank = 2;
        final Position nextPosition = new Position(currentRank + 2, 'e');

        assertThatThrownBy(() -> pawn.capture(currentPosition, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("대각선으로 이동하는 거리가 1칸이 아닙니다.");
    }

    @Test
    @DisplayName("첫 번째 턴이 아니고, 한 칸 이동이 아닌 경우, 예외를 발생시킨다.")
    void moveCountException() {
        final Position currentPositionNotFirstTurn = new Position(3, 'g');
        final Pawn pawnNotFirstTurn = new Pawn(currentPositionNotFirstTurn);
        final Position nextPosition = new Position(5, 'g');

        assertThatThrownBy(() -> pawnNotFirstTurn.move(currentPositionNotFirstTurn, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰은 앞으로 1칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("위치가 주어질 때, 해당 위치에 자신이 존재하는지 확인한다.")
    void checkMyPosition() {
        final Position checkingPosition = new Position(2, 'g');

        assertThat(pawn.exist(checkingPosition)).isTrue();
    }

    @Test
    @DisplayName("폰이 맞다면, true 를 반환한다.")
    void isPawn() {
        assertThat(
                pawn.isPawn()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideNotPawnPiece")
    @DisplayName("폰이 아니면, false 를 반환한다.")
    void isNotPawn(final Piece piece) {
        assertThat(piece.isPawn()).isFalse();
    }

    static Stream<Arguments> provideNotPawnPiece() {
        final Position currentPosition = new Position(2, 'g');
        return Stream.of(Arguments.of(
                new Bishop(currentPosition),
                new King(currentPosition),
                new Queen(currentPosition),
                new Rook(currentPosition)
        ));
    }
}
