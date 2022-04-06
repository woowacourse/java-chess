package chess.domain.state;

import static chess.domain.board.BoardFixtures.createCatchKingBoard;
import static chess.domain.board.File.A;
import static chess.domain.board.File.D;
import static chess.domain.board.File.E;
import static chess.domain.board.PositionFixtures.initialBlackKing;
import static chess.domain.board.PositionFixtures.initialBlackPawn;
import static chess.domain.board.PositionFixtures.initialBlackQueen;
import static chess.domain.board.PositionFixtures.initialWhitePawn;
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

class BlackTurnTest {

	@Test
	void start() {
		Board board = new Board(BoardFactory.initiate());
		State state = new BlackTurn(board);

		assertThatThrownBy(() -> state.start(board))
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("게임이 이미 시작되었습니다.");
	}

	@Test
	void moveSamePosition() {
		Board board = new Board(BoardFactory.initiate());
		State state = new BlackTurn(board);
		Position blackPawn = initialBlackPawn;
		Position target = initialBlackPawn;

		assertThatThrownBy(() -> state.move(blackPawn, target))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("출발지와 같은 곳으로 이동할 수 없습닌다.");
	}

	@Test
	void moveBlackToWhite() {
		Board board = new Board(BoardFactory.initiate());
		State state = new BlackTurn(board);
		Position blackPawn = initialBlackPawn;
		Position target = Position.of(SIX, A);

		assertThat(state.move(blackPawn, target)).isInstanceOf(WhiteTurn.class);
	}

	@Test
	void moveBlackCatchKing() {
		Board board = new Board(createCatchKingBoard());
		State state = new BlackTurn(board);
		Position blackKing = Position.of(FIVE, E);
		Position whiteKing = Position.of(FOUR, D);

		assertThat(state.move(blackKing, whiteKing)).isInstanceOf(KingDeath.class);
	}

	@Test
	void moveWithEnemyPiece() {
		Board board = new Board(BoardFactory.initiate());
		State state = new BlackTurn(board);
		Position whitePawn = initialWhitePawn;
		Position target = Position.of(THREE, A);

		assertThatThrownBy(() -> state.move(whitePawn, target))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("상대 팀의 기물을 옮길 수 없습니다.");
	}

	@Test
	void moveCatchSameTeamPiece() {
		Board board = new Board(BoardFactory.initiate());
		State state = new BlackTurn(board);
		Position blackKing = initialBlackKing;
		Position blackQueen = initialBlackQueen;

		assertThatThrownBy(() -> state.move(blackKing, blackQueen))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}

	@Test
	void createStatus() {
		Board board = new Board(BoardFactory.initiate());
		State state = new BlackTurn(board);

		assertDoesNotThrow(state::createStatus);
	}

	@Test
	void finish() {
		Board board = new Board(BoardFactory.initiate());
		State state = new BlackTurn(board);

		assertThat(state.finish()).isInstanceOf(EndGame.class);
	}

	@Test
	void isFinished() {
		Board board = new Board(BoardFactory.initiate());
		State state = new BlackTurn(board);

		assertThat(state.isFinished()).isFalse();
	}

	@Test
	void judgeWinnerWithRunning() {
		Board board = new Board(BoardFactory.initiate());
		State state = new BlackTurn(board);

		assertThatThrownBy(state::judgeWinner)
				.isInstanceOf(IllegalStateException.class)
				.hasMessageContaining("아직 종료되지 않은 게임입니다.");
	}
}
