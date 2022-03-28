package domain.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import chess.domain.state.State;
import chess.domain.state.WhiteTurn;
import org.junit.jupiter.api.Test;

class EndGameTest {

	@Test
	void isFinished() {
		State state = new WhiteTurn();
		State endGame = state.finish();

		assertThat(endGame.isFinished()).isTrue();
	}

	@Test
	void play() {
		State state = new WhiteTurn();
		State endGame = state.finish();

		assertThatThrownBy(() -> endGame.play(new Pawn(Team.BLACK), new Pawn(Team.WHITE)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("현재는 게임을 진행할 수 없습니다.");
	}

	@Test
	void finish() {
		State state = new WhiteTurn();
		State endGame = state.finish();

		assertThatThrownBy(endGame::finish)
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}
}
