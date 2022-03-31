package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import chess.domain.command.SingleCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FinishedTest {

	@Test
	@DisplayName("Finished 상태에서 proceed를 하면 예외가 발생한다.")
	void finished_proceed() {
		State state = State.create()
			.proceed(SingleCommand.END);
		assertThatThrownBy(() -> state.proceed(SingleCommand.END))
			.isInstanceOf(IllegalStateException.class);
	}
}
