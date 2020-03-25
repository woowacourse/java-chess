package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KingTest {
	@Test
	@DisplayName("킹 생성")
	void constructor() {
		assertThat(new King(Position.from("e1"), Color.WHITE)).isInstanceOf(King.class);
	}
}
