package chess.domain.piece;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.B3;
import static chess.fixture.PositionFixtures.H1;
import static chess.fixture.PositionFixtures.H8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {
    @DisplayName("퀸은 대각선 관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsDiagonal_From_StartPosition() {
        Queen queen = new Queen(Team.WHITE);
        assertThat(queen.canMove(A1, H8)).isTrue();
    }

    @DisplayName("퀸은 수직관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsStraight_From_StartPosition() {
        Queen queen = new Queen(Team.WHITE);
        assertThat(queen.canMove(A1, H1)).isTrue();
    }

    @DisplayName("퀸은 대각선 혹은 직선 관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsNotDiagonalAndIsNotStraight_From_StartPosition() {
        Queen queen = new Queen(Team.WHITE);
        assertThat(queen.canMove(A1, B3)).isFalse();
    }
}
