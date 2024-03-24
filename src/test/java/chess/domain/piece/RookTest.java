package chess.domain.piece;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.A3;
import static chess.fixture.PositionFixtures.B1;
import static chess.fixture.PositionFixtures.B2;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RookTest {
    @DisplayName("룩은 북쪽 방향으로 이동할 수 있다")
    @Test
    void should_RookisNotReachableNorth() {
        Rook rook = new Rook(Team.WHITE);
        assertThat(rook.isNotReachable(A1, A3)).isFalse();
    }

    @DisplayName("룩은 남쪽 방향으로 이동할 수 있다")
    @Test
    void should_RookisNotReachableSouth() {
        Rook rook = new Rook(Team.WHITE);
        assertThat(rook.isNotReachable(A3, A1)).isFalse();
    }

    @DisplayName("룩은 서쪽 방향으로 이동할 수 있다")
    @Test
    void should_RookisNotReachableWest() {
        Rook rook = new Rook(Team.WHITE);
        assertThat(rook.isNotReachable(B1, A1)).isFalse();
    }

    @DisplayName("룩은 동쪽 방향으로 이동할 수 있다")
    @Test
    void should_RookisNotReachableEast() {
        Rook rook = new Rook(Team.WHITE);
        assertThat(rook.isNotReachable(A1, B1)).isFalse();
    }

    @DisplayName("실패 : 룩은 직선관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsNotStraight_From_StartPosition() {
        Rook rook = new Rook(Team.WHITE);
        assertThat(rook.isNotReachable(A1, B2)).isTrue();
    }
}
