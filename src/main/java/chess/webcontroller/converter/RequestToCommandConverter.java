package chess.webcontroller.converter;

import java.util.Map;

import chess.converter.StringToPositionConverter;
import chess.domain.command.Command;
import chess.domain.command.Move;
import spark.Request;

public class RequestToCommandConverter {

	private static final String SOURCE = "source";
	private static final String TARGET = "target";

	public static Command from(Request request) {
		Map<String, String> moveCommand = RequestToMapConverter.ofMultiple(request);

		return new Move(
			StringToPositionConverter.from(moveCommand.get(SOURCE)),
			StringToPositionConverter.from(moveCommand.get(TARGET))
		);
	}
}
