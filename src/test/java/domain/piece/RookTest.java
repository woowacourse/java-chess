package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class RookTest {

	@Test
	void checkBlackTeamSymbol() {
		Rook rook = new Rook("Black");
		assertThat(rook.getSymbol()).isEqualTo("R");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Rook rook = new Rook("White");
		assertThat(rook.getSymbol()).isEqualTo("r");
	}
}
