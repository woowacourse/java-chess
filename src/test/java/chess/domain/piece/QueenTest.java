package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QueenTest {
	@Test
	@DisplayName("퀸 생성")
	void constructor() {
		assertThat(new Queen(Position.from("d1"), Color.WHITE)).isInstanceOf(Queen.class);
	}
}
