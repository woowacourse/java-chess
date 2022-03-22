package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import chess.domain.board.coordinate.Column;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.board.coordinate.Row;

class CoordinateTest {

	@Test
	void create() {
		Coordinate coordinate = Coordinate.of(Column.A, Row.ONE);
		assertThat(coordinate).isEqualTo(Coordinate.of(Column.A, Row.ONE));
	}
}
