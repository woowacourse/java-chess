package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.command.End;
import chess.domain.command.Start;

public class RunningTest {

	@Test
	@DisplayName("Running 상태에서 Start 커맨드를 받을 수 없다")
	void running_cannot_start() {
		GameState state = new Ready(new HashMap<>())
			.proceed(new Start());
		assertThatThrownBy(() -> state.proceed(new Start()))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("Running 상태에서 end 커맨드를 받으면 Finished이다.")
	void running_end() {
		GameState state = new Ready(new HashMap<>())
			.proceed(new Start());

		assertThat(state.proceed(new End()))
			.isInstanceOf(Finished.class);
	}
}
