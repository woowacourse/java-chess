package chess.controller;

import java.util.Arrays;

public enum Command {
	START("start"),
	END("end"),
	MOVE("move [a-h][1-8] [a-h][1-8]"),
	STATUS("status");

	private final String command;

	Command(String command) {
		this.command = command;
	}

	public static Command of(String input) {
		return Arrays.stream(values())
			.filter(value -> input.matches(value.command))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 명령어 입력입니다."));
	}

	public boolean isNotFirst() {
		return !(START.equals(this) || END.equals(this));
	}

	public boolean isEnd() {
		return END.equals(this);
	}

	public boolean isNotPlay() {
		return !(MOVE.equals(this) || STATUS.equals(this));
	}

	public boolean isStatus() {
		return STATUS.equals(this);
	}
}
