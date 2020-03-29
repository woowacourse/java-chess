package chess.domain.piece.state;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
class InitialTest {
	@DisplayName("initial인지 물어보면 true를 반환하는지 확인")
	@Test
	void isInitialTest() {
		assertThat(new Initial().isInitial()).isTrue();
	}
}