package domain.point;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static testAssistant.creationAssistant.createPoint;

class PointTest {

	@Test
	void test() {
		assertThat(new Point(Row.EIGHT, Column.A)).isEqualTo(new Point(Row.EIGHT, Column.A));
	}

	@Test
	void add() {
		Point point = createPoint("b2");
		Point expect = createPoint("a1");

		assertThat(point.add(1, -1)).isEqualTo(expect);
	}
}