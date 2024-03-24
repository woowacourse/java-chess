package domain.direction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StraightDirectionTest {

    @Nested
    @DisplayName("직진 방향을 얻을 수 없다.")
    class InvalidInput {

        @DisplayName("좌표간의 차이가 없는 경우")
        @Test
        void getDirection_WhenSameCoordinate() {
            assertThatThrownBy(() -> StraightDirection.getDirection(0, 0))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }

        @ParameterizedTest
        @CsvSource(value = {"1,3", "-1,-1"}, delimiter = ',')
        @DisplayName("행 또는 열의 차이 중 하나가 0이 아닌 경우")
        void getDirection_WhenOtherCase(int rowDifference, int columnDifference) {
            assertThatThrownBy(() -> StraightDirection.getDirection(rowDifference, columnDifference))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동할 수 없는 위치입니다.");
        }
    }

    @Nested
    @DisplayName("위")
    class Up {

        Direction upRightDirection = StraightDirection.UP;

        @DisplayName("행과 열 값의 차이를 이용하여 방향을 얻는다.")
        @Test
        void getDirection() {
            assertThat(StraightDirection.getDirection(-1, 0))
                    .isEqualTo(upRightDirection);
        }

        @DisplayName("이동 거리를 계산한다.")
        @Test
        void calculateDistance() {
            assertThat(upRightDirection.calculateDistance(-2, 0))
                    .isEqualTo(2);
        }
    }

    @Nested
    @DisplayName("아래")
    class Down {

        Direction downDirection = StraightDirection.DOWN;

        @DisplayName("행과 열 값의 차이를 이용하여 방향을 얻는다.")
        @Test
        void getDirection() {
            assertThat(StraightDirection.getDirection(1, 0))
                    .isEqualTo(downDirection);
        }

        @DisplayName("이동 거리를 계산한다.")
        @Test
        void calculateDistance() {
            assertThat(downDirection.calculateDistance(2, 0))
                    .isEqualTo(2);
        }
    }

    @Nested
    @DisplayName("왼쪽")
    class Left {

        Direction leftDirection = StraightDirection.LEFT;

        @DisplayName("행과 열 값의 차이를 이용하여 방향을 얻는다.")
        @Test
        void getDirection() {
            assertThat(StraightDirection.getDirection(0, -1))
                    .isEqualTo(leftDirection);
        }

        @DisplayName("이동 거리를 계산한다.")
        @Test
        void calculateDistance() {
            assertThat(leftDirection.calculateDistance(0, -2))
                    .isEqualTo(2);
        }
    }

    @Nested
    @DisplayName("오른쪽")
    class Right {

        Direction rightDirection = StraightDirection.RIGHT;

        @DisplayName("행과 열 값의 차이를 이용하여 방향을 얻는다.")
        @Test
        void getDirection() {
            assertThat(StraightDirection.getDirection(0, 1))
                    .isEqualTo(rightDirection);
        }

        @DisplayName("이동 거리를 계산한다.")
        @Test
        void calculateDistance() {
            assertThat(rightDirection.calculateDistance(0, 2))
                    .isEqualTo(2);
        }
    }
}
