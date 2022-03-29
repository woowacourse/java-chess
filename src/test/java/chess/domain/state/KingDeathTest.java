package chess.domain.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
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

	@Test
	void finish() {
		State state = new WhiteTurn();
		State kingDeath = state.play(new Pawn(Team.WHITE), new King(Team.BLACK));

		assertThatThrownBy(kingDeath::finish)
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}
}
