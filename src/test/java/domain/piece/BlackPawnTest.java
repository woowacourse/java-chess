package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.coordinate.Coordinate;
import domain.direction.DiagonalDirection;
import domain.direction.StraightDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BlackPawnTest {

    @DisplayName("시작 위치의 폰이 아니면 2칸 아래로 이동할 수 없다.")
    @Test
    void cantMoveWhenNotInitialPawn() {
        Coordinate start = new Coordinate(3, 3);
        Coordinate destination = new Coordinate(5, 3);
        BlackPawn blackPawn = new BlackPawn();

        assertThatThrownBy(() -> blackPawn.getDirection(start, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없는 위치입니다.");
    }

    @Nested
    @DisplayName("블랙 폰이 이동할 수 있는 방향을 확인한다.")
    class GetDirectionTest {
        BlackPawn blackPawn = new BlackPawn();
        Coordinate start;
        Coordinate destination;

        @DisplayName("아래로 이동할 수 있다.")
        @Test
        void moveToDown() {
            start = new Coordinate(3, 1);
            destination = new Coordinate(4, 1);

            assertThat(blackPawn.getDirection(start, destination)).isEqualTo(StraightDirection.DOWN);
        }

        @DisplayName("시작 위치의 폰은 2칸 아래로 이동할 수 있다.")
        @Test
        void cantMoveWhenInitialPawn() {
            start = new Coordinate(1, 3);
            destination = new Coordinate(3, 3);

            assertThat(blackPawn.getDirection(start, destination)).isEqualTo(StraightDirection.DOWN);
        }

        @DisplayName("왼쪽 아래 대각선으로 이동할 수 있다.")
        @Test
        void moveToDownLeftDiagonal() {
            start = new Coordinate(3, 2);
            destination = new Coordinate(4, 1);

            assertThat(blackPawn.getDirection(start, destination)).isEqualTo(DiagonalDirection.DOWN_LEFT);
        }

        @DisplayName("오른쪽 아래 대각선으로 이동할 수 있다.")
        @Test
        void moveToDownRightDiagonal() {
            start = new Coordinate(3, 2);
            destination = new Coordinate(4, 3);

            assertThat(blackPawn.getDirection(start, destination)).isEqualTo(DiagonalDirection.DOWN_RIGHT);
        }
    }
}
