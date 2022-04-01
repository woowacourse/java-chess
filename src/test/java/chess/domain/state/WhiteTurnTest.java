package chess.domain.state;

import static chess.domain.board.BoardFixtures.createCatchKingBoard;
import static chess.domain.board.File.A;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.PositionFixtures.initialBlackPawn;
import static chess.domain.board.PositionFixtures.initialWhiteKing;
import static chess.domain.board.PositionFixtures.initialWhitePawn;
import static chess.domain.board.PositionFixtures.initialWhiteQueen;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.FOUR;
import static chess.domain.board.Rank.SIX;
import static chess.domain.board.Rank.THREE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import org.junit.jupiter.api.Test;

class WhiteTurnTest {

	@Test
	void start() {
		Board board = new Board(BoardFactory.initiate());
		State state = new WhiteTurn(board);

		assertThatThrownBy(() -> state.start(board))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 시작되었습니다.");
	}

	@Test
	void playWhiteToBlack() {
		Board board = new Board(BoardFactory.initiate());
		State state = new WhiteTurn(board);
		Position whitePawn = initialWhitePawn;
		Position target = Position.of(THREE, A);

		assertThat(state.play(whitePawn, target)).isInstanceOf(BlackTurn.class);
	}

	@Test
	void playWhiteCatchKing() {
		Board board = new Board(createCatchKingBoard());
		State state = new WhiteTurn(board);
		Position whiteKing = Position.of(FOUR, D);
		Position blackKing = Position.of(FIVE, E);

		assertThat(state.play(whiteKing, blackKing)).isInstanceOf(KingDeath.class);
	}

	@Test
	void playWithEnemyPiece() {
		Board board = new Board(BoardFactory.initiate());
		State state = new WhiteTurn(board);
		Position blackPawn = initialBlackPawn;
		Position target = Position.of(SIX, A);

		assertThatThrownBy(() -> state.play(blackPawn, target))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("상대 팀의 기물을 옮길 수 없습니다.");
	}

	@Test
	void playCatchSameTeamPiece() {
		Board board = new Board(BoardFactory.initiate());
		State state = new WhiteTurn(board);
		Position whiteKing = initialWhiteKing;
		Position whiteQueen = initialWhiteQueen;

		assertThatThrownBy(() -> state.play(whiteKing, whiteQueen))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}

	@Test
	void createStatus() {
		Board board = new Board(BoardFactory.initiate());
		State state = new WhiteTurn(board);

		assertDoesNotThrow(state::createStatus);
	}

	@Test
	void finish() {
		Board board = new Board(BoardFactory.initiate());
		State state = new WhiteTurn(board);

		assertThat(state.finish()).isInstanceOf(EndGame.class);
	}

	@Test
	void isFinished() {
		Board board = new Board(BoardFactory.initiate());
		State state = new WhiteTurn(board);

		assertThat(state.isFinished()).isFalse();
	}

	@Test
	void judgeWinnerWithRunning() {
		Board board = new Board(BoardFactory.initiate());
		State state = new WhiteTurn(board);

		assertThatThrownBy(state::judgeWinner)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("아직 종료되지 않은 게임입니다.");
	}
}
