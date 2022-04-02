package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

class PieceTest {

	@Test
	void isAlly() {
		Piece white = new Pawn(Team.WHITE);

		assertAll(
				() -> assertThat(white.isAlly(Team.BLACK)).isFalse(),
				() -> assertThat(white.isAlly(Team.WHITE)).isTrue()
		);
	}
}
