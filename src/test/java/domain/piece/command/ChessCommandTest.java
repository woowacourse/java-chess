package domain.piece.command;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import domain.command.ChessCommand;
import domain.command.InvalidCommandException;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class ChessCommandTest {
	@DisplayName("start, end, status 중 하나가 입력됐을 경우 Command 반환")
	@ParameterizedTest
	@CsvSource({"move, MOVE", "status, STATUS", "end, END"})
	void of_ValidInputCommand_returnCommand(String inputCommand, ChessCommand chessCommand) {
		assertThat(ChessCommand.ofChessCommand(inputCommand)).isEqualTo(chessCommand);
	}

	@DisplayName("move, status, end 외의 명령어가 입력됐을 경우 InvalidCommandException 발생")
	@ParameterizedTest
	@ValueSource(strings = {"start", "123", "run"})
	void of_InvalidInputCommand_returnCommand(String inputCommand) {
		assertThatThrownBy(() -> ChessCommand.ofChessCommand(inputCommand))
			.isInstanceOf(InvalidCommandException.class)
			.hasMessage(InvalidCommandException.INVALID_CHESS_COMMAND);
	}
}
