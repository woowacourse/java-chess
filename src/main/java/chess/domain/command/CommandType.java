package chess.domain.command;

import java.util.stream.Stream;

public enum CommandType {
	START,
	RESUME,
	END,
	MOVE,
	STATUS;

	public static CommandType of(String input) {
		return Stream.of(CommandType.values())
			.filter(command -> command.isEqualName(input))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 커맨드입니다."));
	}

	private boolean isEqualName(String input) {
		return getLowerCaseName().equals(input);
	}

	private String getLowerCaseName() {
		return name().toLowerCase();
	}
}
