package chess;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {
	@Test
	void 생성() {
		Position position = new Position(1, 8);
		assertThat(position).isEqualTo(new Position(1, 8));
	}
}
