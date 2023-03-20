package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class RelativePositionTest {

	@Nested
	@DisplayName("단위 상대 위치로 변환하는 toUnit 메서드 테스트")
	class toUnitTest {

		@Test
		@DisplayName("(0, 0)을 (0, 0)으로 변환한다.")
		void toUnitTest1() {
			RelativePosition relativePosition = new RelativePosition(0, 0);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(0, 0));
		}

		@Test
		@DisplayName("(0, 3)을 (0, 1)로 변환한다.")
		void toUnitTest2() {
			RelativePosition relativePosition = new RelativePosition(0, 3);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(0, 1));
		}

		@Test
		@DisplayName("(3, 0)을 (1, 0)으로 변환한다.")
		void toUnitTest3() {
			RelativePosition relativePosition = new RelativePosition(3, 0);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(1, 0));
		}

		@Test
		@DisplayName("(2, 4)를 (1, 2)로 변환한다.")
		void toUnitTest4() {
			RelativePosition relativePosition = new RelativePosition(2, 4);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(1, 2));
		}

		@Test
		@DisplayName("(3, 5)를 (3, 5)로 변환한다.")
		void toUnitTest5() {
			RelativePosition relativePosition = new RelativePosition(3, 5);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(3, 5));
		}

		@Test
		@DisplayName("(-2, -4)를 (-1, -2)로 변환한다.")
		void toUnitTest6() {
			RelativePosition relativePosition = new RelativePosition(-2, -4);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(-1, -2));
		}

		@Test
		@DisplayName("(-2, 4)를 (-1, 2)로 변환한다.")
		void toUnitTest7() {
			RelativePosition relativePosition = new RelativePosition(-2, 4);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(-1, 2));
		}

		@Test
		@DisplayName("(2, -4)를 (1, -2)로 변환한다.")
		void toUnitTest8() {
			RelativePosition relativePosition = new RelativePosition(2, -4);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(1, -2));
		}

		@Test
		@DisplayName("(-3, 7)를 (-3, 7)로 변환한다.")
		void toUnitTest9() {
			RelativePosition relativePosition = new RelativePosition(-3, 7);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(-3, 7));
		}
	}
}
