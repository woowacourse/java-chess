package domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domain.board.fixture.PawnBoard;
import domain.board.fixture.RookBoard;
import domain.command.MoveCommand;
import domain.piece.position.InvalidPositionException;
import domain.piece.team.Team;

public class BoardTest {
	private BoardGame boardGame;
	private Board board;

	@BeforeEach
	void setUp() {
		boardGame = new BoardGame();
		board = boardGame.getBoard();
	}

	@DisplayName("입력한 source 위치에 말이 없으면 예외 발생")
	@ParameterizedTest
	@CsvSource({"move d5 a1", "move c5 a1", "move f6 a1"})
	void move_InvalidPosition_ExceptionThrown(MoveCommand moveCommand) {
		assertThatThrownBy(() -> boardGame.move(moveCommand, Team.WHITE))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_SOURCE_POSITION);
	}

	@DisplayName("현재 턴과 움직일 말의 팀이 일치하지 않으면 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"move a1 b1", "move h1 b1", "move e2 b1"})
	void move_InvalidTurn_ExceptionThrown(MoveCommand moveCommand) {
		assertThatThrownBy(() -> boardGame.move(moveCommand, Team.BLACK))
			.isInstanceOf(InvalidTurnException.class)
			.hasMessage(InvalidTurnException.INVALID_TURN);
	}

	@DisplayName("처음 상태의 보드판 점수 확인")
	@Test
	void getScore_InitialBoard_IsBlack38AndWhit38() {
		assertThat(boardGame.calculateScore().get(Team.WHITE)).isEqualTo(38.0);
		assertThat(boardGame.calculateScore().get(Team.BLACK)).isEqualTo(38.0);
	}

	@DisplayName("White팀 Rook 2개, Black팀 Rook 1개가 생존했을 때 점수 확인")
	@Test
	void getScore_Black1RookAndWhite2Rook_Black5White10() {
		BoardGame boardGame = new BoardGame(new RookBoard().create());
		assertThat(boardGame.calculateScore().get(Team.WHITE)).isEqualTo(10);
		assertThat(boardGame.calculateScore().get(Team.BLACK)).isEqualTo(5);
	}

	@DisplayName("King이 살았을 때 isKingAlive를 호출하면 true반환")
	@Test
	void isKingAlive_KingIsAlive_ReturnTrue() {
		assertThat(board.isKingAlive()).isTrue();
	}

	@DisplayName("King이 죽었을 때 isKingAlive를 호출하면 false반환")
	@Test
	void isKingAlive_KingIsDead_ReturnFalse() {
		BoardGame boardWithoutKing = new BoardGame(new PawnBoard().create());
		assertThat(boardWithoutKing.isKingAlive()).isFalse();
	}
}
