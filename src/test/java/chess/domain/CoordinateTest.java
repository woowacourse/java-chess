package chess.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {
	@Test
	void 생성() {
		Coordinate coordinate = Coordinate.getCoordinate(2);
		assertThat(coordinate).isEqualTo(Coordinate.getCoordinate(2));
	}

	@Test
	void 좌표값이_최솟값보다_작은_경우() {
		assertThrows(IllegalArgumentException.class, () -> Coordinate.getCoordinate(0));
	}

	@Test
	void 좌표값이_최댓값보다_큰_경우() {
		assertThrows(IllegalArgumentException.class, () -> Coordinate.getCoordinate(9));
	}

	@Test
	void 증가하는_방향으로_갈_수_있는_경우() {
		Coordinate coordinate = Coordinate.getCoordinate(1);
		assertTrue(coordinate.canMove( 1));
	}

	@Test
	void 증가하는_방향으로_갈_수_없는_경우() {
		Coordinate coordinate = Coordinate.getCoordinate(8);
		assertFalse(coordinate.canMove( 1));
	}

	@Test
	void 감소하는_방향으로_갈_수_있는_경우() {
		Coordinate coordinate = Coordinate.getCoordinate(8);
		assertTrue(coordinate.canMove( -1));
	}

	@Test
	void 감소하는_방향으로_갈_수_없는_경우() {
		Coordinate coordinate = Coordinate.getCoordinate(1);
		assertFalse(coordinate.canMove( -1));
	}

	@Test
	void 움직이지_않는_경우() {
		Coordinate coordinate = Coordinate.getCoordinate(1);
		assertTrue(coordinate.canMove( 0));
	}
}
