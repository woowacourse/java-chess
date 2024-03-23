package chess.domain.piece;

import static chess.fixture.PositionFixtures.A3;
import static chess.fixture.PositionFixtures.B1;
import static chess.fixture.PositionFixtures.B2;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KnightTest {
    @DisplayName("나이트는 L자 모양으로 이동할 수 있다")
    @Test
    void should_CanMoveLShapePosition() {
        Position start = B1;
        Position dest = A3;
        Knight knight = new Knight(Team.BLACK);

        assertThat(knight.canMove(start, dest)).isTrue();
    }

    @DisplayName("실패 : 나이트가 행마법을 벗어난 목적지로 이동할 수 없다")
    @Test
    void should_CanNotMoveToWrongDestinationn() {
        Position start = B1;
        Position dest = B2;
        Knight knight = new Knight(Team.BLACK);

        assertThat(knight.canMove(start, dest)).isFalse();
    }
}
