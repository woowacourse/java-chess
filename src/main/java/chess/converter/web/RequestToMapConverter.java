package chess.converter.web;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

import spark.Request;

public class RequestToMapConverter {

	private static final String REQUEST_DELIMITER = "&";
	private static final String COMMAND_DELIMITER = "=";
	private static final int KEY = 0;
	private static final int VALUE = 1;

	public static Map<String, String> ofSingle(Request request) {
		return Stream.of(request.body().strip())
			.map(command -> command.split(COMMAND_DELIMITER))
			.collect(toMap(command -> command[KEY], command -> command[VALUE]));
	}

	public static Map<String, String> ofMultiple(Request request) {
		return Arrays.stream(request.body().strip().split(REQUEST_DELIMITER))
			.map(command -> command.split(COMMAND_DELIMITER))
			.collect(toMap(command -> command[KEY], command -> command[VALUE]));

	}
}
