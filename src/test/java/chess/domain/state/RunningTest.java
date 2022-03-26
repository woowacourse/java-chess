package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.command.End;
import chess.domain.command.Start;

public class RunningTest {

	@Test
	@DisplayName("Running 상태에서 Start 커맨드를 받을 수 없다")
	void running_cannot_start() {
		State state = State.create()
			.proceed(new Start());
		assertThatThrownBy(() -> state.proceed(new Start()))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("Running 상태에서 end 커맨드를 받으면 Finished이다.")
	void running_end() {
		State state = State.create()
			.proceed(new Start());

		assertThat(state.proceed(new End()))
			.isInstanceOf(Finished.class);
	}
}
