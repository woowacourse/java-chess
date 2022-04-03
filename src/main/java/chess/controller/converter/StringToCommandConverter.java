package chess.controller.converter;

import java.util.Arrays;
import java.util.List;

import chess.converter.StringToPositionConverter;
import chess.domain.command.Command;
import chess.domain.command.End;
import chess.domain.command.Move;
import chess.domain.command.Start;
import chess.domain.command.Status;

public enum StringToCommandConverter {

	START("start", new Start()),
	END("end", new End()),
	STATUS("status", new Status()),
	MOVE("move");

	private static final String INVALID_COMMAND = "유효한 커맨드가 아닙니다.";
	private static final String DELIMITER = " ";

	private static final int FROM_POSITION_INDEX = 1;
	private static final int TO_POSITION_INDEX = 2;
	private static final int MOVE_COMMAND_SIZE = 3;

	private final String input;
	private Command command;

	StringToCommandConverter(String input, Command command) {
		this.input = input;
		this.command = command;
	}

	StringToCommandConverter(String input) {
		this.input = input;
	}

	public static Command from(String input) {
		return Arrays.stream(values())
			.filter(value -> input.equals(value.input) && !input.equals(MOVE.input))
			.map(value -> value.command)
			.findAny()
			.orElseGet(() -> checkIsMoveCommand(input));
	}

	private static Command checkIsMoveCommand(String input) {
		List<String> commands = Arrays.asList(input.split(DELIMITER));
		if (input.startsWith(MOVE.input) && commands.size() == MOVE_COMMAND_SIZE) {
			return new Move(
				StringToPositionConverter.from(commands.get(FROM_POSITION_INDEX)),
				StringToPositionConverter.from(commands.get(TO_POSITION_INDEX))
			);
		}
		throw new IllegalArgumentException(INVALID_COMMAND);
	}
}
