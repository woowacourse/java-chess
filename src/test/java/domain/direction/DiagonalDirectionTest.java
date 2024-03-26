package domain.direction;

import static domain.direction.DiagonalDirection.DOWN_LEFT;
import static domain.direction.DiagonalDirection.DOWN_RIGHT;
import static domain.direction.DiagonalDirection.UP_LEFT;
import static domain.direction.DiagonalDirection.UP_RIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiagonalDirectionTest {

    @Nested
    @DisplayName("대각선 방향을 얻을 수 없다.")
    class InvalidInput {

        @DisplayName("좌표간의 차이가 없는 경우")
        @Test
        void getDirection_WhenSameCoordinate() {
            assertThatThrownBy(() -> DiagonalDirection.getDirection(0, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"1,0", "-1,0"}, delimiter = ',')
        @DisplayName("상,하로만 이동한 경우")
        void getDirection_WhenOnlyUpOrDown(int rowDifference, int columnDifference) {
            assertThatThrownBy(() -> DiagonalDirection.getDirection(rowDifference, columnDifference))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"0,1", "0,-1"}, delimiter = ',')
        @DisplayName("좌,우로만 이동한 경우")
        void getDirection_WhenOnlyLeftOrRight(int rowDifference, int columnDifference) {
            assertThatThrownBy(() -> DiagonalDirection.getDirection(rowDifference, columnDifference))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"1,2", "-3,1"}, delimiter = ',')
        @DisplayName("상,하 방향 이동 값과 좌,우 방향 이동 값이 다른 경우")
        void getDirection_WhenOtherCase(int rowDifference, int columnDifference) {
            assertThatThrownBy(() -> DiagonalDirection.getDirection(rowDifference, columnDifference))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }
    }

    @Nested
    @DisplayName("오른쪽 위 대각선")
    class UpRightDiagonal {

        Direction upRightDirection = UP_RIGHT;

        @DisplayName("행과 열 값의 차이를 이용하여 방향을 얻는다.")
        @Test
        void getDirection() {
            assertThat(DiagonalDirection.getDirection(-1, 1))
                    .isEqualTo(upRightDirection);
        }

        @DisplayName("오른쪽 위 대각선 방향으로의 거리를 구한다.")
        @Test
        void calculateDistance() {
            assertThat(upRightDirection.calculateDistance(-3, 3))
                    .isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("왼쪽 위 대각선")
    class UpLeftDiagonal {

        Direction upLeftDirection = UP_LEFT;

        @DisplayName("행과 열 값의 차이를 이용하여 방향을 얻는다.")
        @Test
        void getDirection() {
            assertThat(DiagonalDirection.getDirection(-1, -1))
                    .isEqualTo(upLeftDirection);
        }

        @DisplayName("왼쪽 위 대각선 방향으로의 거리를 구한다.")
        @Test
        void calculateDistance() {
            assertThat(upLeftDirection.calculateDistance(-3, -3))
                    .isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("오른쪽 아래 대각선")
    class DownRightDiagonal {

        Direction downRightDirection = DOWN_RIGHT;

        @DisplayName("행과 열 값의 차이를 이용하여 방향을 얻는다.")
        @Test
        void getDirection() {
            assertThat(DiagonalDirection.getDirection(1, 1))
                    .isEqualTo(downRightDirection);
        }

        @DisplayName("오른쪽 아래 대각선 방향으로의 거리를 구한다.")
        @Test
        void calculateDistance() {
            assertThat(downRightDirection.calculateDistance(3, 3))
                    .isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("왼쪽 아래 대각선")
    class DownLeftDiagonal {

        Direction downLeftDirection = DOWN_LEFT;

        @DisplayName("행과 열 값의 차이를 이용하여 방향을 얻는다.")
        @Test
        void getDirection() {
            assertThat(DiagonalDirection.getDirection(1, -1))
                    .isEqualTo(downLeftDirection);
        }

        @DisplayName("오른쪽 아래 대각선 방향으로의 거리를 구한다.")
        @Test
        void calculateDistance() {
            assertThat(downLeftDirection.calculateDistance(3, -3))
                    .isEqualTo(3);
        }
    }
}
