package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import domain.direction.DiagonalDirection;
import domain.direction.StraightDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("킹은 두 칸 이상 이동할 수 없다.")
    @Test
    void cantMove() {
        King king = new King(Color.WHITE);
        Coordinate start = new Coordinate(3, 3);
        Coordinate destination = new Coordinate(5, 3);

        assertThatThrownBy(() -> king.getDirection(start, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @Nested
    @DisplayName("킹이 이동할 수 있는 방향을 확인한다.")
    class CantMoveTest {
        King king = new King(Color.BLACK);
        Coordinate start = new Coordinate(3, 3);
        Coordinate destination;

        @DisplayName("위 방향")
        @Test
        void up() {
            destination = new Coordinate(2, 3);
            assertThat(king.getDirection(start, destination)).isEqualTo(StraightDirection.UP);
        }

        @DisplayName("아래 방향")
        @Test
        void down() {
            destination = new Coordinate(4, 3);
            assertThat(king.getDirection(start, destination)).isEqualTo(StraightDirection.DOWN);
        }

        @DisplayName("왼쪽 방향")
        @Test
        void left() {
            destination = new Coordinate(3, 2);
            assertThat(king.getDirection(start, destination)).isEqualTo(StraightDirection.LEFT);
        }

        @DisplayName("오른쪽 방향")
        @Test
        void diagonal() {
            destination = new Coordinate(3, 4);
            assertThat(king.getDirection(start, destination)).isEqualTo(StraightDirection.RIGHT);
        }

        @DisplayName("왼쪽 위 대각선 방향")
        @Test
        void upLeftDiagonal() {
            destination = new Coordinate(2, 2);
            assertThat(king.getDirection(start, destination)).isEqualTo(DiagonalDirection.UP_LEFT);
        }

        @DisplayName("오른쪽 위 대각선 방향")
        @Test
        void upRightDiagonal() {
            destination = new Coordinate(2, 4);
            assertThat(king.getDirection(start, destination)).isEqualTo(DiagonalDirection.UP_RIGHT);
        }

        @DisplayName("왼쪽 아래 대각선 방향")
        @Test
        void downLeftDiagonal() {
            destination = new Coordinate(4, 2);
            assertThat(king.getDirection(start, destination)).isEqualTo(DiagonalDirection.DOWN_LEFT);
        }

        @DisplayName("오른쪽 아래 대각선 방향")
        @Test
        void downRightDiagonal() {
            destination = new Coordinate(4, 4);
            assertThat(king.getDirection(start, destination)).isEqualTo(DiagonalDirection.DOWN_RIGHT);
        }
    }
}
