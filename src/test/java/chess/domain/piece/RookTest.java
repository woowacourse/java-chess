package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("성공 : 룩은 직선관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsStraight_From_StartPosition() {
        Rook testRook = new Rook(Team.WHITE);
        Position startPosition = Position.of(0, 0);
        Position verticalDestination = Position.of(7, 0);
        Position horizontalDestination = Position.of(0, 7);

        assertAll(
                () -> assertThat(testRook.canMove(startPosition, verticalDestination)).isTrue(),
                () -> assertThat(testRook.canMove(startPosition, horizontalDestination)).isTrue()
        );
    }

    @DisplayName("실패 : 룩은 직선관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsNotStraight_From_StartPosition() {
        Rook testRook = new Rook(Team.WHITE);
        Position startPosition = Position.of(0, 0);
        Position notVerticalDestination = Position.of(7, 1);
        Position notHorizontalDestination = Position.of(1, 7);

        assertAll(
                () -> assertThat(testRook.canMove(startPosition, notVerticalDestination)).isFalse(),
                () -> assertThat(testRook.canMove(startPosition, notHorizontalDestination)).isFalse()
        );
    }
}
