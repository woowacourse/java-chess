package chess.domain.state;

import static chess.domain.board.BoardFactory.createCatchKingBoard;
import static chess.domain.board.PositionFixtures.initialBlackKing;
import static chess.domain.board.PositionFixtures.initialBlackPawn;
import static chess.domain.board.PositionFixtures.initialBlackQueen;
import static chess.domain.board.PositionFixtures.initialWhitePawn;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.board.InitialBoard;
import chess.domain.board.Position;
import org.junit.jupiter.api.Test;

class BlackTurnTest {

	@Test
	void isFinished() {
		Board board = new Board(InitialBoard.createBoard());
		State state = new BlackTurn(board);

		assertThat(state.isFinished()).isFalse();
	}

	@Test
	void playBlackToWhite() {
		Board board = new Board(InitialBoard.createBoard());
		State state = new BlackTurn(board);
		Position blackPawn = initialBlackPawn;
		Position target = Position.of(6, 1);

		assertThat(state.play(blackPawn, target)).isInstanceOf(WhiteTurn.class);
	}

	@Test
	void playBlackCatchKing() {
		Board board = new Board(createCatchKingBoard());
		State state = new BlackTurn(board);
		Position blackKing = Position.of(5, 5);
		Position whiteKing = Position.of(4, 4);

		assertThat(state.play(blackKing, whiteKing)).isInstanceOf(KingDeath.class);
	}

	@Test
	void playWithEnemyPiece() {
		Board board = new Board(InitialBoard.createBoard());
		State state = new BlackTurn(board);
		Position whitePawn = initialWhitePawn;
		Position target = Position.of(3, 1);

		assertThatThrownBy(() -> state.play(whitePawn, target))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("상대 팀의 기물을 옮길 수 없습니다.");
	}

	@Test
	void playCatchSameTeamPiece() {
		Board board = new Board(InitialBoard.createBoard());
		State state = new BlackTurn(board);
		Position blackKing = initialBlackKing;
		Position blackQueen = initialBlackQueen;

		assertThatThrownBy(() -> state.play(blackKing, blackQueen))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("같은 팀의 기물을 잡을 수 없습니다.");
	}

	@Test
	void finish() {
		Board board = new Board(InitialBoard.createBoard());
		State state = new BlackTurn(board);

		assertThat(state.finish()).isInstanceOf(EndGame.class);
	}

	@Test
	void judgeWinnerWithRunning() {
		Board board = new Board(InitialBoard.createBoard());
		State state = new BlackTurn(board);

		assertThatThrownBy(state::judgeWinner)
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessageContaining("아직 종료되지 않은 게임입니다.");
	}
}
