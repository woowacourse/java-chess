package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionGapTest {

	@DisplayName("오른쪽 위 대각선")
	@Test
	void calculateDirectionTest() {
		PositionGap positionGap = new PositionGap(3, 3);

		assertThat(positionGap.calculateDirection(Arrays.asList(Direction.values())))
			.isEqualTo(Direction.RIGHT_UP);
	}

	@DisplayName("왼쪽 아래 대각선")
	@Test
	void calculateDirectionTest2() {
		PositionGap positionGap = new PositionGap(-2, -2);

		assertThat(positionGap.calculateDirection(Arrays.asList(Direction.values())))
			.isEqualTo(Direction.LEFT_DOWN);
	}

	@DisplayName("위쪽")
	@Test
	void calculateDirectionTest3() {
		PositionGap positionGap = new PositionGap(3, 0);

		assertThat(positionGap.calculateDirection(Arrays.asList(Direction.values())))
			.isEqualTo(Direction.UP);
	}

	@DisplayName("오른쪽")
	@Test
	void calculateDirectionTest4() {
		PositionGap positionGap = new PositionGap(0, 7);

		assertThat(positionGap.calculateDirection(Arrays.asList(Direction.values())))
			.isEqualTo(Direction.RIGHT);
	}

	@DisplayName("오른쪽 아래 대각선")
	@Test
	void calculateDirectionTest5() {
		PositionGap positionGap = new PositionGap(-5, 5);

		assertThat(positionGap.calculateDirection(Arrays.asList(Direction.values())))
			.isEqualTo(Direction.RIGHT_DOWN);
	}

	@DisplayName("아래쪽")
	@Test
	void calculateDirectionTest6() {
		PositionGap positionGap = new PositionGap(-7, 0);

		assertThat(positionGap.calculateDirection(Arrays.asList(Direction.values())))
			.isEqualTo(Direction.DOWN);
	}

	@DisplayName("왼쪽")
	@Test
	void calculateDirectionTest7() {
		PositionGap positionGap = new PositionGap(0, -4);
		assertThat(positionGap.calculateDirection(Arrays.asList(Direction.values())))
			.isEqualTo(Direction.LEFT);
	}

	@DisplayName("왼쪽 위 대각선")
	@Test
	void calculateDirectionTest8() {
		PositionGap positionGap = new PositionGap(7, -7);
		assertThat(positionGap.calculateDirection(Arrays.asList(Direction.values())))
			.isEqualTo(Direction.LEFT_UP);
	}
}
