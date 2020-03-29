package domain.command;

import domain.command.exceptions.CommandTypeException;

import java.util.Arrays;
import java.util.function.Predicate;

public enum CommandType {
	START("start"::equals),
	END("end"::equals),
	STATUS("status"::equals),
	MOVE(string -> "move".equals(string.split(" ")[0]));

	private final Predicate<String> predicate;

	CommandType(Predicate<String> predicate) {
		this.predicate = predicate;
	}

	public static CommandType getInstance(String command) {
		return Arrays.stream(values())
				.filter(value -> value.predicate.test(command))
				.findFirst()
				.orElseThrow(() -> new CommandTypeException("올바르지 않은 명령어입니다."));
	}
}
