package chess.view;

import java.util.stream.Stream;

public enum Option {
	STATUS("status"),
	MOVE("move"),
	START("start"),
	END("end");

	private final String option;

	Option(final String option) {
		this.option = option;
	}

	public static boolean isStartOrEnd(String input) {
		return Stream.of(START, END)
				.noneMatch(option -> option.option.equals(input));
	}

	public static boolean isMoveOrStatus(String input) {
		return Stream.of(MOVE, STATUS)
				.noneMatch(option -> option.option.equals(input));
	}

	public String getOption() {
		return option;
	}
}
