package chess.domain.state;

import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.File.F;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.FOUR;
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
		Position whiteKing = Position.of(FOUR, D);
		Position blackKing = Position.of(FIVE, E);
		State kingDeath = state.play(whiteKing, blackKing);

		assertThatThrownBy(() -> kingDeath.start(board))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}

	@Test
	void play() {
		Board board = new Board(BoardFixtures.createCatchKingBoard());
		State state = new WhiteTurn(board);
		Position whiteKing = Position.of(FOUR, D);
		Position blackKing = Position.of(FIVE, E);
		State kingDeath = state.play(whiteKing, blackKing);

		assertThatThrownBy(() -> kingDeath.play(Position.of(FIVE, E), Position.of(FIVE, F)))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}

	@Test
	void createStatus() {
		Board board = new Board(BoardFixtures.createCatchKingBoard());
		State state = new WhiteTurn(board);
		Position whiteKing = Position.of(FOUR, D);
		Position blackKing = Position.of(FIVE, E);
		State kingDeath = state.play(whiteKing, blackKing);

		assertThatThrownBy(kingDeath::createStatus)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}

	@Test
	void finish() {
		Board board = new Board(BoardFixtures.createCatchKingBoard());
		State state = new WhiteTurn(board);
		Position whiteKing = Position.of(FOUR, D);
		Position blackKing = Position.of(FIVE, E);
		State kingDeath = state.play(whiteKing, blackKing);

		assertThatThrownBy(kingDeath::finish)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 종료되었습니다.");
	}

	@Test
	void isFinished() {
		Board board = new Board(BoardFixtures.createCatchKingBoard());
		State state = new WhiteTurn(board);
		Position whiteKing = Position.of(FOUR, D);
		Position blackKing = Position.of(FIVE, E);
		State kingDeath = state.play(whiteKing, blackKing);

		assertThat(kingDeath.isFinished()).isTrue();
	}

	@Test
	void judgeWinnerWithFinished() {
		Board board = new Board(BoardFixtures.createCatchKingBoard());
		State state = new WhiteTurn(board);
		Position whiteKing = Position.of(FOUR, D);
		Position blackKing = Position.of(FIVE, E);
		State kingDeath = state.play(whiteKing, blackKing);

		assertThat(kingDeath.judgeWinner()).isEqualTo(Team.WHITE);
	}
}
