package domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PawnTest {
	@DisplayName("폰 생성")
	@Test
	void constructor_CreatePawn_Success() {
		Assertions.assertThat(new Pawn()).isInstanceOf(Pawn.class);
	}
}
