package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import chess.domain.command.SingleCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RunningTest {

	@Test
	@DisplayName("Running 상태에서 Start 커맨드를 받을 수 없다")
	void running_cannot_start() {
		State state = State.create()
			.proceed(SingleCommand.START);
		assertThatThrownBy(() -> state.proceed(SingleCommand.START))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("Running 상태에서 end 커맨드를 받으면 Finished이다.")
	void running_end() {
		State state = State.create()
			.proceed(SingleCommand.START);

		assertThat(state.proceed(SingleCommand.END))
			.isInstanceOf(Finished.class);
	}
}
