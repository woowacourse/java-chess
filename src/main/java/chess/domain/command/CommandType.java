package chess.domain.command;

import java.util.stream.Stream;

public enum CommandType {
	START,
	END,
	MOVE,
	STATUS;

	public static CommandType of(String input) {
		return Stream.of(CommandType.values())
				.filter(command -> command.isSameName(input))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드입니다."));
	}

	private boolean isSameName(String input) {
		return getLowerCaseName().equals(input);
	}

	private String getLowerCaseName() {
		return name().toLowerCase();
	}
}
