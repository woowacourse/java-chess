package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("성공 : 비숍은 대각선 관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsDiagonal_From_StartPosition() {
        Bishop testBishop = new Bishop(Team.WHITE);
        Position startPosition = Position.of(0, 0);
        Position diagonalPosition = Position.of(7, 7);

        assertThat(testBishop.canMove(startPosition, diagonalPosition)).isTrue();
    }

    @DisplayName("실패 : 룩은 직선관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsDiagonal_From_StartPosition() {
        Bishop testBishop = new Bishop(Team.WHITE);
        Position startPosition = Position.of(0, 0);
        Position diagonalPosition = Position.of(7, 6);

        assertThat(testBishop.canMove(startPosition, diagonalPosition)).isFalse();
    }
}
