package chess.gamestate;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.piece.Pawn;
import chess.team.Team;

class GameStateTest {

	@DisplayName("boolean 타입으로 게임상태를 가져오는지 확인")
	@ParameterizedTest
	@CsvSource(value = {"true,RUNNING_BLACK_TURN,RUNNING_WHITE_TURN", "true,RUNNING_WHITE_TURN,RUNNING_BLACK_TURN",
		"false,RUNNING_WHITE_TURN,END"})
	void of(boolean running, GameState existing, GameState expect) {
		GameState actual = existing.of(running);
		assertThat(actual).isEqualTo(expect);
	}

	@DisplayName("게임이 Running중인 경우 true를 리턴")
	@ParameterizedTest
	@CsvSource(value = {"RUNNING_BLACK_TURN,true", "RUNNING_WHITE_TURN,true", "END,false"})
	void isGameRunning(GameState gameState, boolean expect) {
		assertThat(gameState.isGameRunning()).isEqualTo(expect);
	}

	@DisplayName("피스가 현재 턴인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"RUNNING_BLACK_TURN,BLACK,true"
		, "RUNNING_WHITE_TURN,WHITE,true"
		, "RUNNING_BLACK_TURN,WHITE,false"
		, "RUNNING_WHITE_TURN,BLACK,false"})
	void isTurn(GameState gameState, Team pieceTeam, boolean expect) {
		boolean actual = gameState.isTurn(new Pawn(pieceTeam));
		assertThat(actual).isEqualTo(expect);
	}
}