package chess.domain.state;

import static chess.domain.board.File.A;
import static chess.domain.board.PositionFixtures.initialWhitePawn;
import static chess.domain.board.Rank.THREE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import org.junit.jupiter.api.Test;

class ReadyTest {

	@Test
	void start() {
		State state = new Ready();
		Board board = new Board(BoardFactory.initiate());

		assertThat(state.start(board)).isInstanceOf(WhiteTurn.class);
	}

	@Test
	void play() {
		State state = new Ready();

		assertThatThrownBy(() -> state.play(initialWhitePawn, Position.of(THREE, A)))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임 시작을 먼저 해야 합니다.");
	}

	@Test
	void createStatus() {
		State state = new Ready();

		assertThatThrownBy(state::createStatus)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임 시작을 먼저 해야 합니다.");
	}

	@Test
	void finish() {
		State state = new Ready();

		assertThatThrownBy(state::finish)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임 시작을 먼저 해야 합니다.");
	}

	@Test
	void isFinished() {
		State state = new Ready();

		assertThat(state.isFinished()).isFalse();
	}

	@Test
	void judgeWinner() {
		State state = new Ready();

		assertThatThrownBy(state::judgeWinner)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임 시작을 먼저 해야 합니다.");
	}
}
