package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import domain.coordinate.Coordinate;
import domain.direction.DiagonalDirection;
import domain.direction.StraightDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class WhitePawnTest {

    @DisplayName("시작 위치의 폰이 아니면 2칸 위로 이동할 수 없다.")
    @Test
    void cantMoveWhenNotInitialPawn() {
        Coordinate start = new Coordinate(5, 3);
        Coordinate destination = new Coordinate(3, 3);
        WhitePawn whitePawn = new WhitePawn();

        assertTrue(() -> whitePawn.cantMove(start, destination));
    }

    @Nested
    @DisplayName("화이트 폰이 이동할 수 있는 방향을 확인한다.")
    class GetDirectionTest {
        WhitePawn whitePawn = new WhitePawn();

        @DisplayName("위로 이동할 수 있다.")
        @Test
        void moveToDown() {
            Coordinate start = new Coordinate(3, 1);
            Coordinate destination = new Coordinate(2, 1);

            assertThat(whitePawn.getDirection(start, destination)).isEqualTo(StraightDirection.UP);
        }

        @DisplayName("시작 위치의 폰은 위로 두 칸 이동할 수 있다.")
        @Test
        void moveToDownWhenInitialPawn() {
            Coordinate start = new Coordinate(6, 1);
            Coordinate destination = new Coordinate(4, 1);

            assertThat(whitePawn.getDirection(start, destination)).isEqualTo(StraightDirection.UP);
        }

        @DisplayName("왼쪽 위 대각선으로 이동할 수 있다.")
        @Test
        void moveToDownLeftDiagonal() {
            Coordinate start = new Coordinate(3, 2);
            Coordinate destination = new Coordinate(2, 1);

            assertThat(whitePawn.getDirection(start, destination)).isEqualTo(DiagonalDirection.UP_LEFT);
        }

        @DisplayName("오른쪽 위 대각선으로 이동할 수 있다.")
        @Test
        void moveToDownRightDiagonal() {
            Coordinate start = new Coordinate(3, 2);
            Coordinate destination = new Coordinate(2, 3);

            assertThat(whitePawn.getDirection(start, destination)).isEqualTo(DiagonalDirection.UP_RIGHT);
        }
    }
}
