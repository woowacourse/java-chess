package chess.domain.state;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.command.End;

public class FinishedTest {

	@Test
	@DisplayName("Finished 상태에서 proceed를 하면 예외가 발생한다.")
	void finished_proceed() {
		State state = new Finished(new HashMap<>());
		assertThatThrownBy(() -> state.proceed(new End()))
			.isInstanceOf(UnsupportedOperationException.class);
	}
}
