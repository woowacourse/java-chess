package chess.domain.game;

import java.util.Arrays;
import java.util.List;

public class Game {

	public static final String LOG_DELIMITER = "\n";
	
	private static final String EMPTY_NAME_ERROR = "게임 이름은 빈칸일 수 없습니다.";
	private static final String TOO_LONG_NAME_ERROR = "게임 이름은 10글자를 넘어갈 수 없습니다.";
	private static final int MAX_NAME_LENGTH = 10;
	private static final String COMMAND_DELIMITER = " ";

	private int id;
	private String name;
	private final String commandLog;

	public Game(final String commandLog) {
		this.commandLog = commandLog;
	}

	public Game(final String name, final String commandLog) {
		this(commandLog);
		validateName(name);
		this.name = name;
	}

	public Game(final int id, final String name, final String commandLog) {
		this(name, commandLog);
		this.id = id;
	}

	public Game(final int id, final List<String> commands) {
		this(toCommandFormat(commands));
		this.id = id;
	}

	private static String toCommandFormat(final List<String> commands) {
		return String.join(COMMAND_DELIMITER, commands);
	}

	public static List<String> parseCommand(String log) {
		return Arrays.asList(log.split(COMMAND_DELIMITER));
	}

	private void validateName(final String name) {
		validateEmpty(name);
		validateLength(name);
	}

	private void validateEmpty(final String name) {
		if (name.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_NAME_ERROR);
		}
	}

	private void validateLength(final String name) {
		if (name.length() > MAX_NAME_LENGTH) {
			throw new IllegalArgumentException(TOO_LONG_NAME_ERROR);
		}
	}

	public List<String> parseLog() {
		return Arrays.asList(commandLog.split(LOG_DELIMITER));
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCommandLog() {
		return commandLog;
	}
}
