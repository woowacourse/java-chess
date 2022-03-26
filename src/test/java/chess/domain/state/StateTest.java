package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StateTest {

	@Test
	@DisplayName("State는 생성되면 Ready이다.")
	void ready() {
		State state = State.create();
		assertThat(state).isInstanceOf(Ready.class);
	}
}