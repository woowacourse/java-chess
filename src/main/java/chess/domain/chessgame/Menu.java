package chess.domain.chessgame;

import java.util.Arrays;

public enum Menu {
	END("end"),
	START("start"),
	MOVE("move"),
	STATUS("status");

	private static final String WRONG_MENU_MESSAGE = "잘못된 메뉴 입력입니다.";
	private static final String BLANK = " ";
	private static final int COMMAND_INDEX = 0;

	private final String command;

	Menu(String command) {
		this.command = command;
	}

	public static Menu of(String command) {
		return Arrays.stream(values())
			.filter(menu -> menu.isMatchCommand(command))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(WRONG_MENU_MESSAGE));
	}

	private boolean isMatchCommand(String command) {
		return this.command.equals(command)
			|| this.command.equals(command.split(BLANK)[COMMAND_INDEX]);
	}

}
