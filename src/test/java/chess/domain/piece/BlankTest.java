package chess.domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Color;

class BlankTest {
	@DisplayName("빈 값을 가져오는지 테스트")
	@Test
	void of() {
		Piece blank = Blank.of();
		assertThat(blank).isInstanceOf(Blank.class);
	}

	@DisplayName("해당 Piece가 빈 값인지 아닌지 테스트")
	@Test
	void isBlankTest() {
		Piece blank = Blank.of();
		Piece pawn = new Pawn(Color.WHITE, "p");
		assertThat(blank.isBlank()).isTrue();
		assertThat(pawn.isBlank()).isFalse();
	}
}