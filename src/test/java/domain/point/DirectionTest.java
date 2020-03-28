package domain.point;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static testAssistant.creationAssistant.createDirection;
import static testAssistant.creationAssistant.createPoint;

class DirectionTest {

	@Test
	void of_N() {
		Point from = createPoint("a1");
		Point to = createPoint("a2");
		Direction expect = createDirection("n");

		assertThat(Direction.of(from, to)).isEqualTo(expect);
	}

	@Test
	void of_Ne() {
		Point from = createPoint("a1");
		Point to = createPoint("b2");
		Direction expect = createDirection("ne");

		assertThat(Direction.of(from, to)).isEqualTo(expect);
	}

	@Test
	void of_Knight() {
		Point from = createPoint("a1");
		Point to = createPoint("c2");
		Direction expect = createDirection("knight");

		assertThat(Direction.of(from, to)).isEqualTo(expect);
	}

	@Test
	void of_SamePosition_Else() {
		Point from = createPoint("a1");
		Point to = createPoint("a1");
		Direction expect = createDirection("else");

		assertThat(Direction.of(from, to)).isEqualTo(expect);
	}

	@Test
	void of_ElsePosition_Else() {
		Point from = createPoint("a1");
		Point to = createPoint("h2");
		Direction expect= createDirection("else");

		assertThat(Direction.of(from, to)).isEqualTo(expect);
	}
}