package domain.piece.command;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domain.command.Command;
import domain.command.InvalidCommandException;

public class CommandTest {
	@DisplayName("start, move, status, end 가 입력됐을 경우 Command 반환")
	@ParameterizedTest
	@CsvSource({"start, START", "move, MOVE", "status, STATUS", "end, END"})
	void of_ValidInputGameCommand_ReturnCommand(String inputCommand, Command command) {
		assertThat(Command.of(inputCommand)).isEqualTo(command);
	}

	@DisplayName("유효하지 않은 명령어가 입력됐을 경우 InvalidCommandException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"START", "run", " MoVe",""})
	void of_InvalidInputGameCommand_ExceptionThrown(String inputCommand) {
		assertThatThrownBy(() -> Command.of(inputCommand))
			.isInstanceOf(InvalidCommandException.class)
			.hasMessage(InvalidCommandException.INVALID_COMMAND_TYPE);
	}
}
