package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTypeTest {

	@ParameterizedTest
	@ValueSource(strings = {"start", "end", "move", "status"})
	void of_InputCommand_ReturnInstance(String command) {
		assertThat(CommandType.of(command)).isInstanceOf(CommandType.class);
	}

	@Test
	void of_InvalidCommandType_ExceptionThrown() {
		assertThatThrownBy(() -> CommandType.of("new"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효한 명령어가 아닙니다.");
	}

	@ParameterizedTest
	@CsvSource(value = {"START,true", "END,false", "MOVE,false", "STATUS,false"})
	void isStartCommandType_CommandType_ReturnTrueIfStartCommandType(CommandType commandType,
		boolean expected) {
		assertThat(commandType.isStartCommandType()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,false", "END,false", "MOVE,true", "STATUS,false"})
	void isMoveCommandType_CommandType_ReturnTrueIfMoveCommandType(CommandType commandType,
		boolean expected) {
		assertThat(commandType.isMoveCommandType()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,false", "END,false", "MOVE,false", "STATUS,true"})
	void isStatusCommandType_CommandType_ReturnTrueIfStatusCommandType(CommandType commandType,
		boolean expected) {
		assertThat(commandType.isStatusCommandType()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,false", "END,true", "MOVE,false", "STATUS,false"})
	void isEndCommandType_CommandType_ReturnTrueIfEndCommandType(CommandType commandType,
		boolean expected) {
		assertThat(commandType.isEndCommandType()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,1", "END,1", "MOVE,3", "STATUS,2"})
	void isCorrectArgumentsSize_CorrectCommandArgumentsSize_ReturnTrue(CommandType commandType, int argumentsSize) {
		assertThat(commandType.isCorrectArgumentsSize(argumentsSize)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"START,2", "END,2", "MOVE,1", "STATUS,0"})
	void isCorrectArgumentsSize_IncorrectCommandArgumentsSize_ReturnFalse(CommandType commandType,
		int argumentsSize) {
		assertThat(commandType.isCorrectArgumentsSize(argumentsSize)).isFalse();
	}

}