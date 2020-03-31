package chess.domain.piece.state;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 *    class description
 *
 *    @author AnHyungJu
 */
class MovedTest {
	@DisplayName("initial인지 물어보면 false 반환하는지 확인")
	@Test
	void isInitialTest() {
		assertThat(new Moved().isInitial()).isFalse();
	}
}