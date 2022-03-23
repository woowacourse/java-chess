package domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

public class RookTest {

	@Test
	void checkBlackTeamSymbol() {
		Rook rook = new Rook(Team.Black);
		assertThat(rook.getSymbol()).isEqualTo("R");
	}

	@Test
	void checkWhiteTeamSymbol() {
		Rook rook = new Rook(Team.White);
		assertThat(rook.getSymbol()).isEqualTo("r");
	}
}
