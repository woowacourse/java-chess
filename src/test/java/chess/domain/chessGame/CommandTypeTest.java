package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

class CommandTypeTest {

	@ParameterizedTest
	@NullSource
	void of_NullCommandType_ExceptionThrown(final String command) {
		assertThatThrownBy(() -> CommandType.of(command))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("명령이 null입니다.");
	}

	@Test
	void of_InvalidCommandType_ExceptionThrown() {
		assertThatThrownBy(() -> CommandType.of("new"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효한 명령어가 아닙니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"start", "end", "move", "status"})
	void of_InputCommand_ReturnInstance(final String command) {
		assertThat(CommandType.of(command)).isInstanceOf(CommandType.class);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,true", "END,false", "MOVE,false", "STATUS,false"})
	void isStartCommandType_ThisCommandType_ReturnCompareResult(final CommandType commandType,
		boolean expected) {
		assertThat(commandType.isStartCommandType()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,false", "END,false", "MOVE,true", "STATUS,false"})
	void isMoveCommandType_ThisCommandType_ReturnCompareResult(final CommandType commandType,
		final boolean expected) {
		assertThat(commandType.isMoveCommandType()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,false", "END,false", "MOVE,false", "STATUS,true"})
	void isStatusCommandType_ThisCommandType_ReturnCompareResult(final CommandType commandType,
		final boolean expected) {
		assertThat(commandType.isStatusCommandType()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,false", "END,true", "MOVE,false", "STATUS,false"})
	void isEndCommandType_ThisCommandType_ReturnCompareResult(final CommandType commandType,
		final boolean expected) {
		assertThat(commandType.isEndCommandType()).isEqualTo(expected);
	}

	@ParameterizedTest
	@CsvSource(value = {"START,1", "END,1", "MOVE,3", "STATUS,2"})
	void isCorrectArgumentsSize_CorrectCommandArgumentsSize_ReturnTrue(final CommandType commandType,
		final int argumentsSize) {
		assertThat(commandType.isCorrectArgumentsSize(argumentsSize)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"START,2", "END,2", "MOVE,1", "STATUS,0"})
	void isCorrectArgumentsSize_IncorrectCommandArgumentsSize_ReturnFalse(final CommandType commandType,
		final int argumentsSize) {
		assertThat(commandType.isCorrectArgumentsSize(argumentsSize)).isFalse();
	}

}