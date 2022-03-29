package domain.state.turn;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.Team;
import chess.domain.state.turn.State;
import chess.domain.state.turn.WhiteTurn;
import org.junit.jupiter.api.Test;

class KingDeathTest {

	@Test
	void isFinished() {
		State state = new WhiteTurn();
		State kingDeath = state.play(new Pawn(Team.WHITE), new King(Team.BLACK));
		assertThat(kingDeath.isFinished()).isTrue();
	}

	@Test
	void play() {
		State state = new WhiteTurn();
		State kingDeath = state.play(new Pawn(Team.WHITE), new King(Team.BLACK));
		assertThatThrownBy(() -> kingDeath.play(new Pawn(Team.BLACK), new Pawn(Team.WHITE)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("현재는 게임을 진행할 수 없습니다.");
	}
}
