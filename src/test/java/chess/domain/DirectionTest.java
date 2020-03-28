package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DirectionTest {

	@DisplayName("왼쪽")
	@Test
	void isMatchTest() {
		assertThat(Direction.LEFT.isMatch(0, -6, 1)).isTrue();
	}

	@DisplayName("오른쪽")
	@Test
	void isMatchTest2() {
		assertThat(Direction.RIGHT.isMatch(0, 6, 1)).isTrue();
	}

	@DisplayName("왼쪽 아래 대각선")
	@Test
	void isMatchTest3() {
		assertThat(Direction.LEFT_DOWN.isMatch(-3, -3, 1)).isTrue();
		assertThat(Direction.LEFT_DOWN.isMatch(-3, -5, 1)).isFalse();
	}

	@DisplayName("오른쪽 아래 대각선")
	@Test
	void isMatchTest4() {
		assertThat(Direction.RIGHT_DOWN.isMatch(-3, 3, 1)).isTrue();
	}

	@DisplayName("화이트팀의 경우")
	@Test
	void getPawnDirectionsTest() {
		List<Direction> directions = Direction.getPawnDirections(Team.WHITE);

		assertThat(directions).isEqualTo(Arrays.asList(
			Direction.UP, Direction.LEFT_UP, Direction.RIGHT_UP));
	}

	@DisplayName("블랙팀의 경우")
	@Test
	void getPawnDirectionsTest2() {
		List<Direction> directions = Direction.getPawnDirections(Team.BLACK);

		assertThat(directions).isEqualTo(Arrays.asList(
			Direction.DOWN, Direction.LEFT_DOWN, Direction.RIGHT_DOWN));
	}
}
