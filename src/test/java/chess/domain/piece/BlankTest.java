package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BlankTest {
	@Test
	@DisplayName("move 하는 경우 예외 발생")
	void move() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(
				() -> new Blank(Position.from("a3")).moveTo(Position.from("a4")));
	}
}
