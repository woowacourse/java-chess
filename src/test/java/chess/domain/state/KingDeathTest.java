package chess.domain.state;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.BoardFixtures;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import org.junit.jupiter.api.Test;

class KingDeathTest {

	@Test
	void start() {
		Board board = new Board(BoardFixtures.createCatchKingBoard());
		State state = new WhiteTurn(board);
		Position whiteKing = Position.of(4, 4);
		Position blackKing = Position.of(5, 5);
		State kingDeath = state.play(whiteKing, blackKing);

		assertThatThrownBy(() -> kingDeath.start(board))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}

	@Test
	void play() {
		Board board = new Board(BoardFixtures.createCatchKingBoard());
		State state = new WhiteTurn(board);
		Position whiteKing = Position.of(4, 4);
		Position blackKing = Position.of(5, 5);
		State kingDeath = state.play(whiteKing, blackKing);

		assertThatThrownBy(() -> kingDeath.play(Position.of(5, 5), Position.of(5, 6)))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}

	@Test
	void createStatus() {
		Board board = new Board(BoardFixtures.createCatchKingBoard());
		State state = new WhiteTurn(board);
		Position whiteKing = Position.of(4, 4);
		Position blackKing = Position.of(5, 5);
		State kingDeath = state.play(whiteKing, blackKing);

		assertThatThrownBy(kingDeath::createStatus)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}

	@Test
	void finish() {
		Board board = new Board(BoardFixtures.createCatchKingBoard());
		State state = new WhiteTurn(board);
		Position whiteKing = Position.of(4, 4);
		Position blackKing = Position.of(5, 5);
		State kingDeath = state.play(whiteKing, blackKing);

		assertThatThrownBy(kingDeath::finish)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}

	@Test
	void isFinished() {
		Board board = new Board(BoardFixtures.createCatchKingBoard());
		State state = new WhiteTurn(board);
		Position whiteKing = Position.of(4, 4);
		Position blackKing = Position.of(5, 5);
		State kingDeath = state.play(whiteKing, blackKing);

		assertThat(kingDeath.isFinished()).isTrue();
	}

	@Test
	void judgeWinnerWithFinished() {
		Board board = new Board(BoardFixtures.createCatchKingBoard());
		State state = new WhiteTurn(board);
		Position whiteKing = Position.of(4, 4);
		Position blackKing = Position.of(5, 5);
		State kingDeath = state.play(whiteKing, blackKing);

		assertThat(kingDeath.judgeWinner()).isEqualTo(Team.WHITE);
	}
}
