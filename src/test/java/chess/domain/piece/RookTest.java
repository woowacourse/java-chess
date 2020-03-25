package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookTest {
	@Test
	@DisplayName("룩 생성")
	void constructor() {
		assertThat(new Rook(Position.from("a1"), Color.WHITE)).isInstanceOf(Rook.class);
	}
}
