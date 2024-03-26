package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.coordinate.Coordinate;
import domain.direction.DiagonalDirection;
import org.assertj.core.api.Assertions;
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

    @DisplayName("비숍은 대각선 방향 이외로는 이동할 수 없다.")
    @Test
    void canMove() {
        destination = new Coordinate(3, 2);
        Assertions.assertThatThrownBy(() -> bishop.getDirection(start, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }
}
