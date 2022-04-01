package chess.converter.web;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Map;

import chess.converter.StringToPositionConverter;
import chess.domain.command.Command;
import chess.domain.command.Move;
import spark.Request;

public class RequestToCommandConverter {

	private static final String REQUEST_DELIMITER = "&";
	private static final String COMMAND_DELIMITER = "=";
	public static final int KEY = 0;
	private static final int VALUE = 1;
	private static final String SOURCE = "source";
	private static final String TARGET = "target";

	public static Command from(Request request) {
		Map<String, String> moveCommand = Arrays.stream(request.body().strip().split(REQUEST_DELIMITER))
			.map(command -> command.split(COMMAND_DELIMITER))
			.collect(toMap(command -> command[KEY], command -> command[VALUE]));

		return new Move(
			StringToPositionConverter.from(moveCommand.get(SOURCE)),
			StringToPositionConverter.from(moveCommand.get(TARGET))
		);
	}
}
