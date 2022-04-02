package chess;

import java.util.Arrays;

public enum Command {
	START("start"),
	END("end");

	private final String value;

	Command(String value) {
		this.value = value;
	}

	public static Command from(String value) {
		return Arrays.stream(values())
			.filter(command -> command.value.equals(value))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령입니다."));
	}
}
