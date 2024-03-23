package chess.domain.piece;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.H7;
import static chess.fixture.PositionFixtures.H8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {
    @DisplayName("비숍은 대각선 관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsDiagonal_From_StartPosition() {
        Bishop testBishop = new Bishop(Team.WHITE);
        Position startPosition = A1;
        Position destination = H8;

        assertThat(testBishop.canMove(startPosition, destination)).isTrue();
    }

    @DisplayName("비숍은 대각선 관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsNotDiagonal_From_StartPosition() {
        Bishop testBishop = new Bishop(Team.WHITE);
        Position startPosition = A1;
        Position destination = H7;

        assertThat(testBishop.canMove(startPosition, destination)).isFalse();
    }
}
