package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class KingTest {

	@Test
	void checkBlackTeamSymbol() {
		King king = new King("Black");
		assertThat(king.getSymbol()).isEqualTo("K");
	}

	@Test
	void checkWhiteTeamSymbol() {
		King king = new King("White");
		assertThat(king.getSymbol()).isEqualTo("k");
	}
}
