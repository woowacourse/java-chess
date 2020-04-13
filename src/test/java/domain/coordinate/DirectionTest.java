package domain.coordinate;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static testAssistant.creationAssistant.createDirection;
import static testAssistant.creationAssistant.createPoint;

class DirectionTest {

	@Test
	void of_N() {
		Coordinate from = createPoint("a1");
		Coordinate to = createPoint("a2");
		Direction expect = createDirection("n");

		assertThat(Direction.of(from, to)).isEqualTo(expect);
	}

	@Test
	void of_Ne() {
		Coordinate from = createPoint("a1");
		Coordinate to = createPoint("b2");
		Direction expect = createDirection("ne");

		assertThat(Direction.of(from, to)).isEqualTo(expect);
	}

	@Test
	void of_Knight() {
		Coordinate from = createPoint("a1");
		Coordinate to = createPoint("c2");
		Direction expect = createDirection("knight");

		assertThat(Direction.of(from, to)).isEqualTo(expect);
	}

	@Test
	void of_SamePosition_Else() {
		Coordinate from = createPoint("a1");
		Coordinate to = createPoint("a1");
		Direction expect = createDirection("else");

		assertThat(Direction.of(from, to)).isEqualTo(expect);
	}

	@Test
	void of_ElsePosition_Else() {
		Coordinate from = createPoint("a1");
		Coordinate to = createPoint("h2");
		Direction expect= createDirection("else");

		assertThat(Direction.of(from, to)).isEqualTo(expect);
	}
}