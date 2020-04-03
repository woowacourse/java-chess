package chess.domain.position;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {
	@DisplayName("생성자를 호출한 경우 position 인스턴스 생성")
	@Test
	void contruct() {
		assertThat(new Position(1, 3)).isInstanceOf(Position.class);
	}
}