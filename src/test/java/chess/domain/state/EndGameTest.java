package chess.domain.state;

import static chess.domain.board.PositionFixtures.initialWhitePawn;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.InitialBoard;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

class EndGameTest {

	@Test
	void start() {
		Board board = new Board(InitialBoard.createBoard());
		State state = new WhiteTurn(board);
		State endGame = state.finish();

		assertThatThrownBy(() -> endGame.start(board))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}

	@Test
	void play() {
		Board board = new Board(InitialBoard.createBoard());
		State state = new WhiteTurn(board);
		State endGame = state.finish();

		assertThatThrownBy(() -> endGame.play(initialWhitePawn, Position.of(3, 1)))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}

	@Test
	void createStatus() {
		Board board = new Board(InitialBoard.createBoard());
		State state = new WhiteTurn(board);
		State endGame = state.finish();

		assertThatThrownBy(endGame::createStatus)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}

	@Test
	void finish() {
		Board board = new Board(InitialBoard.createBoard());
		State state = new WhiteTurn(board);
		State endGame = state.finish();

		assertThatThrownBy(endGame::finish)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}

	@Test
	void isFinished() {
		Board board = new Board(InitialBoard.createBoard());
		State state = new WhiteTurn(board);
		State endGame = state.finish();

		assertThat(endGame.isFinished()).isTrue();
	}

	@Test
	void judgeWinnerWithFinished() {
		Board board = new Board(InitialBoard.createBoard());
		State state = new WhiteTurn(board);
		State endGame = state.finish();

		assertThat(endGame.judgeWinner()).isEqualTo(Team.NEUTRALITY);
	}
}
