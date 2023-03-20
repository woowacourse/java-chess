package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RelativePositionTest {

	@Nested
	@DisplayName("단위 상대 위치로 변환하는 toUnit 메서드 테스트")
	class GetGcdDivided {

		@Test
		@DisplayName("(0, 0)을 (0, 0)으로 변환한다.")
		void getGcdDividedTest1() {
			RelativePosition relativePosition = new RelativePosition(0, 0);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(0, 0));
		}

		@Test
		@DisplayName("(0, 3)을 (0, 1)로 변환한다.")
		void getGcdDividedTest2() {
			RelativePosition relativePosition = new RelativePosition(0, 3);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(0, 1));
		}

		@Test
		@DisplayName("(3, 0)을 (1, 0)으로 변환한다.")
		void getGcdDividedTest3() {
			RelativePosition relativePosition = new RelativePosition(3, 0);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(1, 0));
		}

		@Test
		@DisplayName("(2, 4)를 (1, 2)로 변환한다.")
		void getGcdDividedTest4() {
			RelativePosition relativePosition = new RelativePosition(2, 4);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(1, 2));
		}

		@Test
		@DisplayName("(3, 5)를 (3, 5)로 변환한다.")
		void getGcdDividedTest5() {
			RelativePosition relativePosition = new RelativePosition(3, 5);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(3, 5));
		}

		@Test
		@DisplayName("(-2, -4)를 (-1, -2)로 변환한다.")
		void getGcdDividedTest6() {
			RelativePosition relativePosition = new RelativePosition(-2, -4);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(-1, -2));
		}

		@Test
		@DisplayName("(-2, 4)를 (-1, 2)로 변환한다.")
		void getGcdDividedTest7() {
			RelativePosition relativePosition = new RelativePosition(-2, 4);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(-1, 2));
		}

		@Test
		@DisplayName("(2, -4)를 (1, -2)로 변환한다.")
		void getGcdDividedTest8() {
			RelativePosition relativePosition = new RelativePosition(2, -4);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(1, -2));
		}

		@Test
		@DisplayName("(-3, 7)를 (-3, 7)로 변환한다.")
		void getGcdDividedTest9() {
			RelativePosition relativePosition = new RelativePosition(-3, 7);

			assertThat(relativePosition.getGcdDivided()).isEqualTo(new RelativePosition(-3, 7));
		}
	}

	@ParameterizedTest
	@DisplayName("x가 0이고 y가 0을 제외한 -2에서 2 사이라면 true를 반환해야 한다.")
	@CsvSource({"-2", "-1", "1", "2"})
	void isXZeroAndYOneOrTwoTest1(int y) {
		RelativePosition relativePosition = new RelativePosition(0, y);
		assertTrue(relativePosition.isXZeroAndYOneOrTwo());
	}

	@ParameterizedTest
	@DisplayName("x가 0이고 y가 0이나 -3이하 또는 3 이상이라면 false를 반환해야 한다.")
	@CsvSource({"-3", "0", "3"})
	void isXZeroAndYOneOrTwoTest2(int y) {
		RelativePosition relativePosition = new RelativePosition(0, y);
		assertFalse(relativePosition.isXZeroAndYOneOrTwo());
	}

	@Test
	@DisplayName("수직 방향의 변화를 판별해야 한다.")
	void isVerticalTest() {
		RelativePosition vertical = new RelativePosition(0, -2);
		assertTrue(vertical.isVertical());

		RelativePosition nonVertical = new RelativePosition(0, 0);
		assertFalse(nonVertical.isVertical());
	}
}
