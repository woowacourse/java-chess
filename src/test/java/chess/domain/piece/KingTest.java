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

class KingTest {

    private final Position currentPosition = new Position(3, 'c');
    private final King king = new King(currentPosition);

    @ParameterizedTest
    @DisplayName("상하좌우, 대각선 중 한 방향으로 1칸 이동 가능하다.")
    @MethodSource("provideDestinationPosition")
    void move(final Position expected) {
        final Position actual = king.move(currentPosition, expected, Team.WHITE);

        assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> provideDestinationPosition() {
        return Stream.of(Arguments.of(
                new Position(4, 'c'),
                new Position(2, 'c'),
                new Position(3, 'b'),
                new Position(3, 'd'),
                new Position(4, 'b'),
                new Position(4, 'd'),
                new Position(2, 'b'),
                new Position(2, 'd')
        ));
    }

    @Test
    @DisplayName("킹이 이동한 방향이 올바르지 않을 경우, 예외를 발생시킨다.")
    void moveDirectionException() {
        final Position nextPosition = new Position(4, 'a');

        assertThatThrownBy(() -> king.move(currentPosition, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 상하좌우, 대각선 중 한 방향으로 이동해야 합니다.");
    }


    @Test
    @DisplayName("킹이 이동한 칸수가 1칸이 아닐 경우, 예외를 발생시킨다.")
    void moveDistanceException() {
        final Position nextPosition = new Position(5, 'c');

        assertThatThrownBy(() -> king.move(currentPosition, nextPosition, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("킹은 1칸만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("위치가 주어질 때, 해당 위치에 자신이 존재하는지 확인한다.")
    void checkMyPosition() {
        final Position checkingPosition = new Position(3, 'c');

        assertThat(king.exist(checkingPosition)).isTrue();
    }

    @Test
    @DisplayName("킹이 맞다면, true 를 반환한다.")
    void isKing() {
        assertThat(king.isKing()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("provideNotKingPiece")
    @DisplayName("킹이 아니면, false 를 반환한다.")
    void isNotKing(final Piece piece) {
        assertThat(piece.isKing()).isFalse();
    }

    static Stream<Arguments> provideNotKingPiece() {
        final Position currentPosition = new Position(2, 'g');
        return Stream.of(Arguments.of(
                new Bishop(currentPosition),
                new Pawn(currentPosition),
                new Queen(currentPosition),
                new Rook(currentPosition)
        ));
    }
}
