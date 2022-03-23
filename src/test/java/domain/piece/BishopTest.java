package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

class BishopTest {

	@Test
	void checkBlackTeamSymbol() {
		Bishop bishop = new Bishop(Team.Black);
		assertThat(bishop.getSymbol()).isEqualTo("B");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Bishop bishop = new Bishop(Team.White);
		assertThat(bishop.getSymbol()).isEqualTo("b");
	}
}
