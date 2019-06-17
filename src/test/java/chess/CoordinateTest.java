package chess;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CoordinateTest {
	@Test
	void 생성() {
		Coordinate coordinate = new Coordinate(2);
		assertThat(coordinate).isEqualTo(new Coordinate(2));
	}

	@Test
	void 좌표값이_최솟값보다_작은_경우() {
		assertThrows(IllegalArgumentException.class, () -> new Coordinate(0));
	}

	@Test
	void 좌표값이_최댓값보다_큰_경우() {
		assertThrows(IllegalArgumentException.class, () -> new Coordinate(9));
	}
}
