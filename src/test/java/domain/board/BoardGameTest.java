package domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domain.board.fixture.RookBoard;
import domain.command.MoveCommand;
import domain.piece.position.InvalidPositionException;
import domain.piece.team.Team;

public class BoardGameTest {
	private BoardGame boardGame;

	@BeforeEach
	void setUp() {
		boardGame = new BoardGame();
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
}
