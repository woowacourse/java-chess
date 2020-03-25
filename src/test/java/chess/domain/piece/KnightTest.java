package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KnightTest {
	@Test
	@DisplayName("나이트 생성")
	void constructor() {
		assertThat(new Knight(Position.from("b1"), Color.WHITE)).isInstanceOf(Knight.class);
	}
}
