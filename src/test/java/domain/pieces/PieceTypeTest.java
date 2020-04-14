package domain.pieces;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PieceTypeTest {

	@Test
	void ofName() {
		assertThat(PieceType.ofName("pawn")).isEqualTo(PieceType.PAWN);
	}
}