package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import domain.coordinate.Coordinate;
import domain.direction.KnightDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class KnightTest {

    @Nested
    @DisplayName("나이트가 이동할 수 있는 방향을 확인한다.")
    class GetDirectionTest {

        Knight knight = new Knight(Color.BLACK);
        Coordinate start = new Coordinate(3, 3);
        Coordinate destination;

        @DisplayName("왼쪽 위 방향으로 이동한다.")
        @Test
        void upLeftTest() {
            destination = new Coordinate(1, 2);
            assertThat(knight.getDirection(start, destination))
                    .isEqualTo(KnightDirection.UP_LEFT);
        }

        @DisplayName("오른쪽 위 방향으로 이동한다.")
        @Test
        void upRightTest() {
            destination = new Coordinate(1, 4);
            assertThat(knight.getDirection(start, destination))
                    .isEqualTo(KnightDirection.UP_RIGHT);
        }

        @DisplayName("왼쪽 아래 방향으로 이동한다.")
        @Test
        void downLeftTest() {
            destination = new Coordinate(5, 2);
            assertThat(knight.getDirection(start, destination))
                    .isEqualTo(KnightDirection.DOWN_LEFT);
        }

        @DisplayName("오른쪽 아래 방향으로 이동한다.")
        @Test
        void downRightTest() {
            destination = new Coordinate(5, 4);
            assertThat(knight.getDirection(start, destination))
                    .isEqualTo(KnightDirection.DOWN_RIGHT);
        }
    }
}
