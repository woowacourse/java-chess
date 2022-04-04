package chess.domain.state.turn;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.Team;
import org.junit.jupiter.api.Test;

class BlackTurnTest {

	@Test
	void isFinished() {
		State state = new WhiteTurn();
		state = state.play(new Pawn(Team.BLACK));

		assertThat(state.isFinished()).isFalse();
	}

	@Test
	void playBlackToWhite() {
		State state = new WhiteTurn();
		state = state.play(new Pawn(Team.BLACK));

		assertThat(state.play(new Pawn(Team.WHITE))).isInstanceOf(WhiteTurn.class);
	}

	@Test
	void playBlackCatchKing() {
		State state = new WhiteTurn();
		state = state.play(new Pawn(Team.BLACK));

		assertThat(state.play(new King(Team.WHITE))).isInstanceOf(KingDeath.class);
	}
}
