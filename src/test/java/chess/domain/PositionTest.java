package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

	@Test
	@DisplayName("위치를 이동시킨다.")
	void change() {
		Position position = new Position(1, 1);

		position = position.change(1, 2);

		assertThat(position).isEqualTo(new Position(1, 2));
	}
}