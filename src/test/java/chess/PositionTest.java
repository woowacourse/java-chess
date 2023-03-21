package chess;

import static chess.fixture.PositionFixture.A1;
import static chess.fixture.PositionFixture.B3;
import static chess.fixture.PositionFixture.G1;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.path.Movement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("출발점과 도착점을 입력하면, 이동 방향을 알 수 있다.")
    @Test
    void test_convertMovement() {
        Movement movement = B3.convertMovement(A1);

        assertThat(movement).isEqualTo(Movement.UP_UP_RIGHT);
    }

    @DisplayName("출발점과 도착점을 입력하면, 이동 방향을 알 수 있다.")
    @Test
    void test_convertMovement2() {
        Movement movement = G1.convertMovement(A1);

        assertThat(movement).isEqualTo(Movement.RIGHT);
    }
}
