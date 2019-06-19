package chess.pattern;

import java.util.ArrayList;
import java.util.Arrays;

import chess.Path;
import chess.Position;
import chess.exception.NotFoundPathException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RookPatternTest {
	private RookPattern rookPattern;

	@BeforeEach
	void setUp() {
		rookPattern = new RookPattern();
	}

	@Test
	void 이동_가능() {
		Position start = new Position(1, 1);
		Position end = new Position(1, 2);

		Path path = new Path(new ArrayList<>());

		assertThat(rookPattern.move(start, end)).isEqualTo(path);
	}

	@Test
	void 이동_불가능() {
		Position start = new Position(3, 3);
		Position end = new Position(5, 1);

		assertThrows(NotFoundPathException.class, () -> rookPattern.move(start, end));
	}
}