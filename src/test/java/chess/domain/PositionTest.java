package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import chess.domain.position.Position;
import chess.domain.position.Positions;

public class PositionTest {

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
