package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class PawnTest {

	@Test
	void checkBlackTeamSymbol() {
		Pawn pawn = new Pawn("Black");
		assertThat(pawn.getSymbol()).isEqualTo("P");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Pawn pawn = new Pawn("White");
		assertThat(pawn.getSymbol()).isEqualTo("p");
	}
}
