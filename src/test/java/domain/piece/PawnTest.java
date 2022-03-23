package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

public class PawnTest {

	@Test
	void checkBlackTeamSymbol() {
		Pawn pawn = new Pawn(Team.Black);
		assertThat(pawn.getSymbol()).isEqualTo("P");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Pawn pawn = new Pawn(Team.White);
		assertThat(pawn.getSymbol()).isEqualTo("p");
	}
}
