package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import domain.coordinate.Coordinate;
import domain.direction.DiagonalDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    Bishop bishop = new Bishop(Color.BLACK);
    Coordinate start = new Coordinate(3, 3);
    Coordinate destination;

    @DisplayName("비숍은 대각선 방향으로 이동할 수 있다.")
    @Test
    void getDirection() {
        destination = new Coordinate(7, 7);

        assertThat(bishop.getDirection(start, destination)).isEqualTo(DiagonalDirection.DOWN_RIGHT);
    }

    @DisplayName("비숍은 대각선 방향이면 거리와 상관없이 이동 가능하다.")
    @Test
    void canMove() {
        destination = new Coordinate(1, 1);

        assertFalse(() -> bishop.cantMove(start, destination));
    }
}
