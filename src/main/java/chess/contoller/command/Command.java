package chess.contoller.command;

import java.util.Arrays;

public enum Command {
	START,
	MOVE,
	STATUS,
	END;

	public static Command of(String string) {
		return Arrays.stream(values())
				.filter(command -> command.isMatch(string))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("일치하는 실행문이 없습니다."));
	}

	private boolean isMatch(String string) {
		return toString().equalsIgnoreCase(string);
	}
}
