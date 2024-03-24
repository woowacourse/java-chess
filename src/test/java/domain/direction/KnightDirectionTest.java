package domain.direction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightDirectionTest {

    @Nested
    @DisplayName("나이트의 방향을 얻을 수 없다.")
    class InvalidInput {

        @DisplayName("좌표간의 차이가 없는 경우")
        @Test
        void getDirection_WhenSameCoordinate() {
            assertThatThrownBy(() -> KnightDirection.getDirection(0, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"1,0", "-1,0"}, delimiter = ',')
        @DisplayName("상,하로만 이동한 경우")
        void getDirection_WhenOnlyUpOrDown(int rowDifference, int columnDifference) {
            assertThatThrownBy(() -> KnightDirection.getDirection(rowDifference, columnDifference))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"0,1", "0,-1"}, delimiter = ',')
        @DisplayName("좌,우로만 이동한 경우")
        void getDirection_WhenOnlyLeftOrRight(int rowDifference, int columnDifference) {
            assertThatThrownBy(() -> KnightDirection.getDirection(rowDifference, columnDifference))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"1,1", "-3,-3"}, delimiter = ',')
        @DisplayName("대각선으로 이동한 경우")
        void getDirection_WhenOtherCase(int rowDifference, int columnDifference) {
            assertThatThrownBy(() -> KnightDirection.getDirection(rowDifference, columnDifference))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }
    }

    @Nested
    @DisplayName("오른쪽 위")
    class UpRight {

        Direction upRightDirection = KnightDirection.UP_RIGHT;

        @DisplayName("행과 열 값의 차이를 이용하여 방향을 얻는다.")
        @Test
        void getDirection() {
            assertThat(KnightDirection.getDirection(-2, 1))
                    .isEqualTo(upRightDirection);
        }

        @DisplayName("나이트의 이동 거리는 1이다.")
        @Test
        void calculateDistance() {
            assertThat(upRightDirection.calculateDistance(-2, 1))
                    .isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("왼쪽 위")
    class UpLeft {

        Direction upLeftDirection = KnightDirection.UP_LEFT;

        @DisplayName("행과 열 값의 차이를 이용하여 방향을 얻는다.")
        @Test
        void getDirection() {
            assertThat(KnightDirection.getDirection(-2, -1))
                    .isEqualTo(upLeftDirection);
        }

        @DisplayName("나이트의 이동 거리는 1이다.")
        @Test
        void calculateDistance() {
            assertThat(upLeftDirection.calculateDistance(-2, -1))
                    .isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("오른쪽 아래")
    class DownRight {

        Direction downRightDirection = KnightDirection.DOWN_RIGHT;

        @DisplayName("행과 열 값의 차이를 이용하여 방향을 얻는다.")
        @Test
        void getDirection() {
            assertThat(KnightDirection.getDirection(2, 1))
                    .isEqualTo(downRightDirection);
        }

        @DisplayName("나이트의 이동 거리는 1이다.")
        @Test
        void calculateDistance() {
            assertThat(downRightDirection.calculateDistance(2, 1))
                    .isEqualTo(1);
        }
    }

    @Nested
    @DisplayName("왼쪽 아래")
    class DownLeft {

        Direction downLeft = KnightDirection.DOWN_LEFT;

        @DisplayName("행과 열 값의 차이를 이용하여 방향을 얻는다.")
        @Test
        void getDirection() {
            assertThat(KnightDirection.getDirection(2, -1))
                    .isEqualTo(downLeft);
        }

        @DisplayName("나이트의 이동 거리는 1이다.")
        @Test
        void calculateDistance() {
            assertThat(downLeft.calculateDistance(2, -1))
                    .isEqualTo(1);
        }
    }
}
