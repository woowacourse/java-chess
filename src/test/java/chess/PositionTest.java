package chess;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.path.Movement;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @DisplayName("출발점과 도착점을 입력하면, 이동 방향을 알 수 있다.")
    @Test
    void test_convertMovement() {

        Position from = new Position(1, 1);
        Position destination = new Position(2, 3);

        Movement movement = destination.convertMovement(from);

        assertThat(movement).isEqualTo(Movement.UP_UP_RIGHT);
    }

    @DisplayName("출발점과 도착점을 입력하면, 이동 방향을 알 수 있다.")
    @Test
    void test_convertMovement2() {

        Position from = new Position(1, 1);
        Position destination = new Position(7, 1);

        Movement movement = destination.convertMovement(from);

        assertThat(movement).isEqualTo(Movement.RIGHT);
    }
}
