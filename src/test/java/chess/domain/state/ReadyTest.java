package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import chess.domain.command.SingleCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.command.MoveCommand;
import chess.domain.position.Position;

public class ReadyTest {

	@Test
	@DisplayName("Ready는 MoveCommand 커맨드를 받을 수 없다.")
	void ready_cannot_proceed_move() {
		State state = State.create();
		assertThatThrownBy(() -> state.proceed(
			new MoveCommand(new Position(1, 1), new Position(2, 2)))
		).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("Ready가 End 커맨드를 받으면 Finished 상태가 된다.")
	void ready_proceed_end() {
		State state = State.create();
		assertThat(state.proceed(SingleCommand.END))
			.isInstanceOf(Finished.class);
	}

	@Test
	@DisplayName("Ready가 start 커맨드를 받으면 Started 상태가 된다")
	void started() {
		State state = State.create()
			.proceed(SingleCommand.START);
		assertThat(state).isInstanceOf(Running.class);
	}
}
