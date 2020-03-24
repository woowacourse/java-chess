package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionTest {
	@Test
	@DisplayName("위치 정보를 String으로 받아 좌표값으로 변환")
	void PositionReceive() {
		assertThat(new Position("e1")).isEqualTo(new Position(5, 1));
	}
}
