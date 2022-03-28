package domain.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Team;
import chess.domain.state.BlackTurn;
import chess.domain.state.EndGame;
import chess.domain.state.KingDeath;
import chess.domain.state.State;
import chess.domain.state.WhiteTurn;
import org.junit.jupiter.api.Test;

class WhiteTurnTest {

	@Test
	void isFinished() {
		State state = new WhiteTurn();
		assertThat(state.isFinished()).isFalse();
	}

	@Test
	void playWhiteToBlack() {
		State state = new WhiteTurn();

		assertThat(state.play(new Pawn(Team.WHITE), new Pawn(Team.BLACK))).isInstanceOf(BlackTurn.class);
	}

	@Test
	void playWhiteCatchKing() {
		State state = new WhiteTurn();
		assertThat(state.play(new Pawn(Team.WHITE), new King(Team.BLACK))).isInstanceOf(KingDeath.class);
	}

	@Test
	void playWithEnemyPiece() {
		State state = new WhiteTurn();
		assertThatThrownBy(() -> state.play(new Pawn(Team.BLACK), new Pawn(Team.WHITE)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("상대 팀의 기물을 옮길 수 없습니다.");
	}

	@Test
	void playCatchSameTeamPiece() {
		State state = new WhiteTurn();

		assertThatThrownBy(() -> state.play(new Pawn(Team.WHITE), new Pawn(Team.WHITE)))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물로 이동할 수 없습니다.");
	}

	@Test
	void finish() {
		State state = new WhiteTurn();

		assertThat(state.finish()).isInstanceOf(EndGame.class);
	}
}
