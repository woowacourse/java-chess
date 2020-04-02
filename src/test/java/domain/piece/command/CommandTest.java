package domain.piece.command;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
	@ValueSource(strings = {"START", "run", " MoVe", ""})
	void of_InvalidInputGameCommand_ExceptionThrown(String inputCommand) {
		assertThatThrownBy(() -> Command.of(inputCommand))
			.isInstanceOf(InvalidCommandException.class)
			.hasMessage(InvalidCommandException.INVALID_COMMAND_TYPE);
	}

	@DisplayName("START가 isStart()를 호출하면 True반환")
	@Test
	void isStart_InputStart_ReturnTrue() {
		assertThat(Command.START.isStart()).isTrue();
	}

	@DisplayName("START가 아닌 다른 명령어가 isStart()를 호출하면 False반환")
	@ParameterizedTest
	@CsvSource({"STATUS","END","MOVE"})
	void isStart_InputNotStart_ReturnFalse(Command command) {
		assertThat(command.isStart()).isFalse();
	}

	@DisplayName("MOVE가 isMove()를 호출하면 True반환")
	@Test
	void isMove_InputMove_ReturnTrue() {
		assertThat(Command.MOVE.isMove()).isTrue();
	}

	@DisplayName("MOVE가 아닌 다른 명령어가 isMove()를 호출하면 False반환")
	@ParameterizedTest
	@CsvSource({"STATUS","END","START"})
	void isMove_InputNotMove_ReturnFalse(Command command) {
		assertThat(command.isMove()).isFalse();
	}

	@DisplayName("STATUS가 isStatus()를 호출하면 True반환")
	@Test
	void isStatus_InputStatus_ReturnTrue() {
		assertThat(Command.STATUS.isStatus()).isTrue();
	}

	@DisplayName("STATUS가 아닌 다른 명령어가 isStatus()를 호출하면 False반환")
	@ParameterizedTest
	@CsvSource({"MOVE","END","START"})
	void isStatus_InputNotStatus_ReturnFalse(Command command) {
		assertThat(command.isStatus()).isFalse();
	}

	@DisplayName("END가 isEnd()를 호출하면 True반환")
	@Test
	void isEnd_InputEnd_ReturnTrue() {
		assertThat(Command.END.isEnd()).isTrue();
	}

	@DisplayName("END가 아닌 명령어가 isNotEnd()를 호출하면 True반환")
	@ParameterizedTest
	@CsvSource({"MOVE","STATUS","START"})
	void isNotEnd_InputNotEnd_ReturnTrue(Command command) {
		assertThat(command.isNotEnd()).isTrue();
	}
}
