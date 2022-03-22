package chess.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CoordinateTest {

	@Test
	void create() {
		Coordinate coordinate = Coordinate.of(Column.A, Row.ONE);
		assertThat(coordinate).isEqualTo(Coordinate.of(Column.A, Row.ONE));
	}
}
