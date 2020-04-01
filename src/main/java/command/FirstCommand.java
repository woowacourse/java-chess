package command;

import java.util.Arrays;

public enum FirstCommand {
	START("start"),
	END("end");

	private final String command;

	FirstCommand(String command) {
		this.command = command;
	}

	public static FirstCommand of(String inputCommand) {
		return Arrays.stream(values())
			.filter(firstCommand -> firstCommand.command.equals(inputCommand))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("입력을 잘못하셨습니다."));
	}
}
