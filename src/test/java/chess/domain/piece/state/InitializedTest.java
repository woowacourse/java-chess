package chess.domain.piece.state;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
class InitializedTest {
	@DisplayName("isCaptured를 물어보면 false 반환하는지 확인")
	@Test
	void isCapturedTest() {
		assertThat(new Initial().isCaptured()).isFalse();
	}

	@DisplayName("isAlive를 물어보면 true 반환하는지 확인")
	@Test
	void isAliveTest() {
		assertThat(new Initial().isAlive()).isTrue();
	}
}