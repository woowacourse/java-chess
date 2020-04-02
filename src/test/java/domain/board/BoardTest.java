package domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domain.board.fixture.GameRunningBoard;
import domain.board.fixture.KingBoard;
import domain.piece.Piece;
import domain.piece.position.InvalidPositionException;
import domain.piece.team.Team;

public class BoardTest {
	private static Board board;

	@BeforeEach
	void setUp() {
		board = BoardFactory.create();
	}

	@DisplayName("입력한 source 위치에 말이 없으면 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"d5", "c5", "f6"})
	void move_InvalidPosition_ExceptionThrown(String sourcePosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, "a1", Team.WHITE))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_SOURCE_POSITION);
	}

	@DisplayName("현재 턴과 움직일 말의 팀이 일치하지 않으면 예외 발생")
	@ParameterizedTest
	@ValueSource(strings = {"a1", "h1", "e2"})
	void move_InvalidTurn_ExceptionThrown(String sourcePosition) {
		assertThatThrownBy(() -> board.move(sourcePosition, "b1", Team.BLACK))
			.isInstanceOf(InvalidTurnException.class)
			.hasMessage(InvalidTurnException.INVALID_TURN);
	}

	@DisplayName("게임 점수 계산")
	@Test
	void calculateTeamScore_GivenTeam_SumTeamScore() {
		board = new Board(new GameRunningBoard().create().getRanks());
		assertThat(board.calculateTeamScore(Team.BLACK)).isEqualTo(20);
		assertThat(board.calculateTeamScore(Team.WHITE)).isEqualTo(19.5);
	}

	@DisplayName("왕이 잡히면 true 반환")
	@ParameterizedTest
	@CsvSource({"b2,b1,BLACK,true", "b1,b2,WHITE,true", "b2,c1,BLACK,false"})
	void isKingDeadTest(String sourcePosition, String targetPosition, Team team, boolean expected) {
		Board board = new Board(new KingBoard().create().getRanks());

		Rank rank = board.getRanks().get(1);
		Piece piece = board.findPiece("c2", rank);
		rank.getPieces().remove(piece);

		board.move(sourcePosition, targetPosition, team);
		assertThat(board.isKingDead()).isEqualTo(expected);
	}

	@DisplayName("게임 승리 팀 반환")
	@ParameterizedTest
	@CsvSource({"b2,b1,BLACK,BLACK", "b1,b2,WHITE,WHITE"})
	void findWinnerTest(String sourcePosition, String targetPosition, Team turn, Team winner) {
		Board board = new Board(new KingBoard().create().getRanks());

		Rank rank = board.getRanks().get(1);
		Piece piece = board.findPiece("c2", rank);
		rank.getPieces().remove(piece);

		board.move(sourcePosition, targetPosition, turn);
		assertThat(board.findWinner()).isEqualTo(winner);
	}
}