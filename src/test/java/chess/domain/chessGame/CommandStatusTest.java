package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandStatusTest {

	@ParameterizedTest
	@ValueSource(strings = {"start", "end", "move", "status"})
	void of_InputCommand_ReturnInstance(String command) {
		assertThat(CommandStatus.of(command)).isInstanceOf(CommandStatus.class);
	}

	@Test
	void of_InvalidCommandStatus_ExceptionThrown() {
		assertThatThrownBy(() -> CommandStatus.of("new"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효한 명령어가 아닙니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"START,true", "END,false", "MOVE,false", "STATUS,false"})
	void isStartCommandStatus_CommandStatus_ReturnTrueIfStartCommandStatus(CommandStatus commandStatus,
		boolean expected) {
		assertThat(commandStatus.isStartCommandStatus()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,false", "END,false", "MOVE,true", "STATUS,false"})
	void isMoveCommandStatus_CommandStatus_ReturnTrueIfMoveCommandStatus(CommandStatus commandStatus,
		boolean expected) {
		assertThat(commandStatus.isMoveCommandStatus()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,false", "END,false", "MOVE,false", "STATUS,true"})
	void isStatusCommandStatus_CommandStatus_ReturnTrueIfStatusCommandStatus(CommandStatus commandStatus,
		boolean expected) {
		assertThat(commandStatus.isStatusCommandStatus()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,false", "END,true", "MOVE,false", "STATUS,false"})
	void isEndCommandStatus_CommandStatus_ReturnTrueIfEndCommandStatus(CommandStatus commandStatus,
		boolean expected) {
		assertThat(commandStatus.isEndCommandStatus()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,0", "END,0", "MOVE,2", "STATUS,1"})
	void isCorrectArgumentsSize_CorrectCommandArgumentsSize_ReturnTrue(CommandStatus commandStatus, int argumentsSize) {
		assertThat(commandStatus.isCorrectArgumentsSize(argumentsSize)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"START,2", "END,1", "MOVE,1", "STATUS,0"})
	void isCorrectArgumentsSize_IncorrectCommandArgumentsSize_ReturnFalse(CommandStatus commandStatus,
		int argumentsSize) {
		assertThat(commandStatus.isCorrectArgumentsSize(argumentsSize)).isFalse();
	}

}