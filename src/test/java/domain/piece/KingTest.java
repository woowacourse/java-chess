package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.King;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

public class KingTest {

	@Test
	void checkBlackTeamSymbol() {
		King king = new King(Team.Black);
		assertThat(king.getSymbol()).isEqualTo("K");
	}

	@Test
	void checkWhiteTeamSymbol() {
		King king = new King(Team.White);
		assertThat(king.getSymbol()).isEqualTo("k");
	}
}
