package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class KnightTest {

	@Test
	void checkBlackTeamSymbol() {
		Knight knight = new Knight("Black");
		assertThat(knight.getSymbol()).isEqualTo("N");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Knight knight = new Knight("White");
		assertThat(knight.getSymbol()).isEqualTo("n");
	}
}
