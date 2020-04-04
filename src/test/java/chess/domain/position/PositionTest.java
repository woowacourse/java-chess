package chess.domain.position;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Direction;

public class PositionTest {

	@DisplayName("허용되는 않는 숫자값")
	@Test
	void createTest() {
		assertThatThrownBy(() -> Position.of(0, 8))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("허용되지 않는 문자")
	@Test
	void createTest2() {
		assertThatThrownBy(() -> Position.of(0, 'i'))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void plusTest() {
		Position position = Position.of(2, 2);
		assertThat(position.plus(Direction.RIGHT_UP)).isEqualTo(Position.of(3, 3));
	}

	@Test
	void calculateDiffTest() {
		Position position = Position.of(2, 2);
		assertThat(position.calculateDiff(Position.of(4, 4))).isEqualTo(new PositionGap(2, 2));
	}

	@Test
	void calculatePathTest() {
		Position position = Position.of(3, 3);
		Positions actualPositions = position.calculatePath(Position.of(6, 6), Direction.RIGHT_UP);
		Positions expectedPositions = new Positions(
			Arrays.asList(Position.of(4, 4),
				Position.of(5, 5)));

		assertThat(actualPositions).isEqualTo(expectedPositions);
	}
}
