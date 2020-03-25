package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {
	@Test
	@DisplayName("폰 생성")
	void constructor() {
		assertThat(new Pawn(Position.from("a2"), Color.WHITE)).isInstanceOf(Pawn.class);
	}
}
