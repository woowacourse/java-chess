package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RelativePositionTest {

    @Nested
    @DisplayName("단위 상대 위치로 변환하는 toUnit 메서드 테스트")
    class toUnitTest {

        @Test
        @DisplayName("(0, 0)을 (0, 0)으로 변환한다.")
        void toUnitTest1() {
            RelativePosition relativePosition = new RelativePosition(0, 0);

            assertThat(relativePosition.toUnit()).isEqualTo(new RelativePosition(0, 0));
        }

        @Test
        @DisplayName("(0, 3)을 (0, 1)로 변환한다.")
        void toUnitTest2() {
            RelativePosition relativePosition = new RelativePosition(0, 3);

            assertThat(relativePosition.toUnit()).isEqualTo(new RelativePosition(0, 1));
        }

        @Test
        @DisplayName("(3, 0)을 (1, 0)으로 변환한다.")
        void toUnitTest3() {
            RelativePosition relativePosition = new RelativePosition(3, 0);

            assertThat(relativePosition.toUnit()).isEqualTo(new RelativePosition(1, 0));
        }

        @Test
        @DisplayName("(2, 4)를 (1, 2)로 변환한다.")
        void toUnitTest4() {
            RelativePosition relativePosition = new RelativePosition(2, 4);

            assertThat(relativePosition.toUnit()).isEqualTo(new RelativePosition(1, 2));
        }

        @Test
        @DisplayName("(3, 5)를 (3, 5)로 변환한다.")
        void toUnitTest5() {
            RelativePosition relativePosition = new RelativePosition(3, 5);

            assertThat(relativePosition.toUnit()).isEqualTo(new RelativePosition(3, 5));
        }

        @Test
        @DisplayName("(-2, -4)를 (-1, -2)로 변환한다.")
        void toUnitTest6() {
            RelativePosition relativePosition = new RelativePosition(-2, -4);

            assertThat(relativePosition.toUnit()).isEqualTo(new RelativePosition(-1, -2));
        }

        @Test
        @DisplayName("(-2, 4)를 (-1, 2)로 변환한다.")
        void toUnitTest7() {
            RelativePosition relativePosition = new RelativePosition(-2, 4);

            assertThat(relativePosition.toUnit()).isEqualTo(new RelativePosition(-1, 2));
        }

        @Test
        @DisplayName("(2, -4)를 (1, -2)로 변환한다.")
        void toUnitTest8() {
            RelativePosition relativePosition = new RelativePosition(2, -4);

            assertThat(relativePosition.toUnit()).isEqualTo(new RelativePosition(1, -2));
        }

        @Test
        @DisplayName("(-3, 7)를 (-3, 7)로 변환한다.")
        void toUnitTest9() {
            RelativePosition relativePosition = new RelativePosition(-3, 7);

            assertThat(relativePosition.toUnit()).isEqualTo(new RelativePosition(-3, 7));
        }
    }

    @Nested
    @DisplayName("x축을 기준으로 상대 위치를 뒤집어주는 inverseByXAxis 메서드 테스트")
    class inverseByXAxisTest {
        @Test
        @DisplayName("(0, 1)를 (0, -1)으로 변환한다.")
        void inverseByXAxisTest1() {
            RelativePosition relativePosition = new RelativePosition(0, 1);

            assertThat(relativePosition.inverseByXAxis()).isEqualTo(new RelativePosition(0, -1));
        }
    }

    @Nested
    @DisplayName("상대 위치의 방향이 대각선 방향인지 확인하는 isDiagonal 메서드 테스트")
    class isDiagonalTest {
        @Test
        @DisplayName("(3, -3)은 대각선 방향이다.")
        void isDiagonalTest1() {
            RelativePosition relativePosition = new RelativePosition(3, -3);

            assertThat(relativePosition.isDiagonal()).isEqualTo(true);
        }

        @Test
        @DisplayName("(-3, -3)은 대각선 방향이다.")
        void isDiagonalTest2() {
            RelativePosition relativePosition = new RelativePosition(-3, -3);

            assertThat(relativePosition.isDiagonal()).isEqualTo(true);
        }

        @Test
        @DisplayName("(-3, 3)은 대각선 방향이다.")
        void isDiagonalTest3() {
            RelativePosition relativePosition = new RelativePosition(-3, 3);

            assertThat(relativePosition.isDiagonal()).isEqualTo(true);
        }

        @Test
        @DisplayName("(3, 3)은 대각선 방향이다.")
        void isDiagonalTest4() {
            RelativePosition relativePosition = new RelativePosition(3, 3);

            assertThat(relativePosition.isDiagonal()).isEqualTo(true);
        }

        @Test
        @DisplayName("(1, 2)는 대각선 방향이 아니다.")
        void isDiagonalTest5() {
            RelativePosition relativePosition = new RelativePosition(1, 2);

            assertThat(relativePosition.isDiagonal()).isEqualTo(false);
        }
    }
}
