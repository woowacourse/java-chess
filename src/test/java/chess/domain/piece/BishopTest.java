package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopTest {
	@Test
	@DisplayName("비숍 생성")
	void constructor() {
		assertThat(new Bishop(Position.from("c1"), Color.WHITE)).isInstanceOf(Bishop.class);
	}
}
