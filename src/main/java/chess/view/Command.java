package chess.view;

import java.util.Arrays;

public enum Command {

	START("start"),
	END("end");

	private final String value;

	Command(String value) {
		this.value = value;
	}

	public static Command of(String value) {
		return Arrays.stream(Command.values())
			.filter(it -> it.value.equals(value))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("올바르지 않은 명령어 입니다."));
	}
}
