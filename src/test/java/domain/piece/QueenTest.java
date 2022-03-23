package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Queen;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

public class QueenTest {

	@Test
	void checkBlackTeamSymbol() {
		Queen queen = new Queen(Team.Black);
		assertThat(queen.getSymbol()).isEqualTo("Q");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Queen queen = new Queen(Team.White);
		assertThat(queen.getSymbol()).isEqualTo("q");
	}
}
