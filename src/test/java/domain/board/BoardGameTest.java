package domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.command.MoveCommand;
import domain.piece.position.InvalidPositionException;

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
		assertThatThrownBy(() -> boardGame.move(moveCommand))
			.isInstanceOf(InvalidPositionException.class)
			.hasMessage(InvalidPositionException.INVALID_SOURCE_POSITION);
	}
}
