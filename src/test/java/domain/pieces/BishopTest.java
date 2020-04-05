package domain.pieces;

import domain.pieces.exceptions.CanNotMoveException;
import domain.point.Direction;
import domain.point.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static testAssistant.creationAssistant.*;

public class BishopTest {
	Bishop bishop;

	@BeforeEach
	void setUp() {
		bishop = createBishop("white", "a1");
	}

	@Test
	void move() {
		Coordinate coordinate = createPoint("a2");
		Bishop expect = createBishop("white", "a2");

		assertThat(bishop.move(coordinate)).isEqualTo(expect);
	}

	@Test
	@DisplayName("비숍이 갈 수 없는 방향일 시 예외를 던지는지 테스트")
	void canMove_ThrowException() {
		Direction direction = createDirection("n");

		assertThatThrownBy(() -> bishop.validateMoveDirection(direction))
				.isInstanceOf(CanNotMoveException.class);
	}

	@Test
	@DisplayName("비숍이 갈 수 있는 방향일 시 예외를 던지지 않는지 테스트")
	void canMove() {
		Direction direction = createDirection("ne");

		bishop.validateMoveDirection(direction);
	}
}
