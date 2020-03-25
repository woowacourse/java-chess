package chess.domain.position;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class LineFactoryTest {
	@Test
	void columnOf() {
		List<Position> expected = List.of(A3, B3, D3, E3, F3, G3, H3);
		List<Position> actual = LineFactory.columnOf(C3);
		assertThat(actual).containsAll(expected);
	}

	@Test
	void rowOf() {
		List<Position> expected = List.of(C1, C2, C4, C5, C6, C7, C8);
		List<Position> actual = LineFactory.rowOf(C3);
		assertThat(actual).containsAll(expected);
	}

	@Test
	void diagonalsOf() {
		List<Position> expected = List.of(A1, B2, D4, E5, F6, G7, H8, E1, D2, B4, A5);
		List<Position> actual = LineFactory.diagonalsOf(C3);
		assertThat(actual).containsAll(expected);
	}
}