package chess.domain.game;

public class Game {

	private static final String EMPTY_NAME_ERROR = "게임 이름은 빈칸일 수 없습니다.";
	private static final String TOO_LONG_NAME_ERROR = "게임 이름은 10글자를 넘어갈 수 없습니다.";
	private static final int MAX_NAME_LENGTH = 10;

	private int id;
	private final String name;
	private final String commandLog;

	public Game(final String name, final String commandLog) {
		validateName(name);
		this.name = name;
		this.commandLog = commandLog;

	}

	public Game(final int id, final String name, final String commandLog) {
		this(name, commandLog);
		this.id = id;
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
