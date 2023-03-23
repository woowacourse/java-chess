package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    void test_convertMovement() {

        Position from = new Position(1, 1);
        Position destination = new Position(2, 3);

        Movement movement = destination.convertMovement(from);

        assertThat(movement).isEqualTo(Movement.UUR);
    }

    @Test
    void test_convertMovement2() {

        Position from = new Position(1, 1);
        Position destination = new Position(7, 1);

        Movement movement = destination.convertMovement(from);

        assertThat(movement).isEqualTo(Movement.R);
    }
}
