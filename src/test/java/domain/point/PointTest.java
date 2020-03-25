package domain.point;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

	@Test
	void test() {
		assertThat(new Point(Row.EIGHT, Column.A)).isEqualTo(new Point(Row.EIGHT, Column.A));
	}
}