package domain.piece.command;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domain.command.GameCommand;
import domain.command.InvalidCommandException;

public class GameCommandTest {
	@DisplayName("start 혹은 end가 입력됐을 경우 Command 반환")
	@ParameterizedTest
	@CsvSource({"start, START", "end, END"})
	void of_ValidInputCommand_returnCommand(String inputCommand, GameCommand gameCommand) {
		assertThat(GameCommand.ofGameCommand(inputCommand)).isEqualTo(gameCommand);
	}

	@DisplayName("start 혹은 end 외의 명령어가 입력됐을 경우 InvalidCommandException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"status", "move", "run"})
	void of_InvalidInputCommand_returnCommand(String inputCommand) {
		assertThatThrownBy(() -> GameCommand.ofGameCommand(inputCommand))
			.isInstanceOf(InvalidCommandException.class)
			.hasMessage(InvalidCommandException.INVALID_GAME_COMMAND);
	}
}
