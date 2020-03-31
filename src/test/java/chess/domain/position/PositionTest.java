package chess.domain.position;

import static chess.domain.position.File.*;
import static chess.domain.position.Rank.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {
	@DisplayName("행, 열을 입력받아 생성한 Position 객체가 올바르게 생성되는지 테스트")
	@Test
	void ofTest() {
		assertThat(Position.of(A, ONE)).isEqualTo(Position.of(A, ONE));
	}
}