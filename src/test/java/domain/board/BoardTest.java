/*
package domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.board.fixture.PawnBoard;
import domain.board.fixture.RookBoard;
import domain.piece.position.InvalidPositionException;
import domain.piece.position.Position;
import domain.piece.team.Team;

public class BoardTest {
	private Board board = BoardFactory.create();

	@DisplayName("입력한 source 위치에 말이 없으면 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"d5", "c5", "f6"})
	void move_InvalidPosition_ExceptionThrown(Position sourcePosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, Position.of("a1"), Team.WHITE))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_SOURCE_POSITION);
	}

	@DisplayName("현재 턴과 움직일 말의 팀이 일치하지 않으면 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"a1", "h1", "e2"})
	void move_InvalidTurn_ExceptionThrown(Position sourcePosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, Position.of("b1"), Team.BLACK))
			.isInstanceOf(InvalidTurnException.class)
			.hasMessage(InvalidTurnException.INVALID_TURN);
	}

	@DisplayName("처음 상태의 보드판 점수 확인")
	@Test
	void getScore_InitialBoard_IsBlack38AndWhit38() {
		assertThat(board.calculateScore().get(Team.WHITE)).isEqualTo(38.0);
		assertThat(board.calculateScore().get(Team.BLACK)).isEqualTo(38.0);
	}

	@DisplayName("White팀 Rook 2개, Black팀 Rook 1개가 생존했을 때 점수 확인")
	@Test
	void getScore_Black1RookAndWhite2Rook_Black5White10() {
		Board board = new RookBoard().create();
		assertThat(board.calculateScore().get(Team.WHITE)).isEqualTo(10);
		assertThat(board.calculateScore().get(Team.BLACK)).isEqualTo(5);
	}

	@DisplayName("King이 살았을 때 isKingAlive를 호출하면 true반환")
	@Test
	void isKingAlive_KingIsAlive_ReturnTrue() {
		assertThat(board.isKingAlive()).isTrue();
	}

	@DisplayName("King이 죽었을 때 isKingAlive를 호출하면 false반환")
	@Test
	void isKingAlive_KingIsDead_ReturnFalse() {
		Board boardWithoutKing = new PawnBoard().create();
		assertThat(boardWithoutKing.isKingAlive()).isFalse();
	}
}*/
