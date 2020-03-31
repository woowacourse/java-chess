package domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

	@Test
	@DisplayName("Target 까지 Row 거리 계산")
	void getRowDistance() {
		Point point = Point.of("b2");
		Point target = Point.of("b4");
		assertThat(point.getRowDistance(target)).isEqualTo(2);
	}

	@Test
	@DisplayName("Target 까지 Column 거리 계산")
	void getColumnDistance() {
		Point point = Point.of("b2");
		Point target = Point.of("d2");
		assertThat(point.getColumnDistance(target)).isEqualTo(2);
	}

	@Test
	@DisplayName("Row 와 Column index 가져오기")
	void getIndex() {
		Point point = Point.of("c3");
		assertThat(point.getRowIndex()).isEqualTo(3);
		assertThat(point.getColumnIndex()).isEqualTo(3);
	}
}