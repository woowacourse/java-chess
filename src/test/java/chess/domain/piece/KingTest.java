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
    void should_KingCanMoveNorth() {
        King king = new King(Team.BLACK);
        assertThat(king.canMove(A1, A2)).isTrue();
    }

    @DisplayName("킹은 남쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMoveSouth() {
        King king = new King(Team.BLACK);
        assertThat(king.canMove(A2, A1)).isTrue();
    }

    @DisplayName("킹은 서쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMoveWest() {
        King king = new King(Team.BLACK);
        assertThat(king.canMove(B1, A1)).isTrue();
    }

    @DisplayName("킹은 동쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMoveEast() {
        King king = new King(Team.BLACK);
        assertThat(king.canMove(A1, B1)).isTrue();
    }


    @DisplayName("킹은 북동쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMoveNorthEast() {
        King king = new King(Team.BLACK);
        assertThat(king.canMove(B1, C2)).isTrue();
    }

    @DisplayName("킹은 북서쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMoveNorthWest() {
        King king = new King(Team.BLACK);
        assertThat(king.canMove(B1, A2)).isTrue();
    }

    @DisplayName("킹은 남동쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMoveSouthEast() {
        King king = new King(Team.BLACK);
        assertThat(king.canMove(B2, C1)).isTrue();
    }

    @DisplayName("킹은 남서쪽 방향으로 한칸 이동할 수 있다")
    @Test
    void should_KingCanMoveSouthWest() {
        King king = new King(Team.BLACK);
        assertThat(king.canMove(B2, A1)).isTrue();
    }

    @DisplayName("킹은 행마법을 벗어난 목적지로 이동할 수 없다")
    @Test
    void should_CanNotMoveToWrongDestination() {
        King king = new King(Team.BLACK);
        assertThat(king.canMove(A1, D3)).isFalse();
    }
}
