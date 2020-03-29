package chess.domain.piece.state;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
class CapturedTest {
	@DisplayName("isCaptured를 물어보면 true를 반환하는지 확인")
	@Test
	void isCapturedTest() {
		assertThat(new Captured().isCaptured()).isTrue();
	}

	@DisplayName("isAlive를 물어보면 false를 반환하는지 확인")
	@Test
	void isAliveTest() {
		assertThat(new Captured().isAlive()).isFalse();
	}
}