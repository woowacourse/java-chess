package chess.domain.piece;

import static chess.fixture.PositionFixtures.A1;
import static chess.fixture.PositionFixtures.A2;
import static chess.fixture.PositionFixtures.B1;
import static chess.fixture.PositionFixtures.B2;
import static chess.fixture.PositionFixtures.C1;
import static chess.fixture.PositionFixtures.C2;
import static chess.fixture.PositionFixtures.D3;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    @DisplayName("킹은 북쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingIsNotReachableNorth() {
        King king = new King(Team.BLACK);
        assertThat(king.isNotReachable(A1, A2)).isFalse();
    }

    @DisplayName("킹은 남쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingIsNotReachableSouth() {
        King king = new King(Team.BLACK);
        assertThat(king.isNotReachable(A2, A1)).isFalse();
    }

    @DisplayName("킹은 서쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingIsNotReachableWest() {
        King king = new King(Team.BLACK);
        assertThat(king.isNotReachable(B1, A1)).isFalse();
    }

    @DisplayName("킹은 동쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingIsNotReachableEast() {
        King king = new King(Team.BLACK);
        assertThat(king.isNotReachable(A1, B1)).isFalse();
    }


    @DisplayName("킹은 북동쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingIsNotReachableNorthEast() {
        King king = new King(Team.BLACK);
        assertThat(king.isNotReachable(B1, C2)).isFalse();
    }

    @DisplayName("킹은 북서쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingIsNotReachableNorthWest() {
        King king = new King(Team.BLACK);
        assertThat(king.isNotReachable(B1, A2)).isFalse();
    }

    @DisplayName("킹은 남동쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingIsNotReachableSouthEast() {
        King king = new King(Team.BLACK);
        assertThat(king.isNotReachable(B2, C1)).isFalse();
    }

    @DisplayName("킹은 남서쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingIsNotReachableSouthWest() {
        King king = new King(Team.BLACK);
        assertThat(king.isNotReachable(B2, A1)).isFalse();
    }

    @DisplayName("킹은 행마법을 벗어난 목적지로 이동할 수 없다")
    @Test
    void should_CanNotMoveToWrongDestination() {
        King king = new King(Team.BLACK);
        assertThat(king.isNotReachable(A1, D3)).isTrue();
    }
}
