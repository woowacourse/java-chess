package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Knight;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

public class KnightTest {

	@Test
	void checkBlackTeamSymbol() {
		Knight knight = new Knight(Team.Black);
		assertThat(knight.getSymbol()).isEqualTo("N");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Knight knight = new Knight(Team.White);
		assertThat(knight.getSymbol()).isEqualTo("n");
	}
}
