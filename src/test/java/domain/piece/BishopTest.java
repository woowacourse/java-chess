package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class BishopTest {

	@Test
	void checkBlackTeamSymbol() {
		Bishop bishop = new Bishop("Black");
		assertThat(bishop.getSymbol()).isEqualTo("B");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Bishop bishop = new Bishop("White");
		assertThat(bishop.getSymbol()).isEqualTo("b");
	}
}
