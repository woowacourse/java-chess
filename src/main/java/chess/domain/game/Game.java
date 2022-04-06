package chess.domain.game;

public class Game {

	private static final String EMPTY_NAME_ERROR = "게임 이름은 빈칸일 수 없습니다.";

	private int id;
	private final String name;
	private final String commandLog;

	public Game(final int id, final String name, final String commandLog) {
		this.id = id;
		this.name = name;
		this.commandLog = commandLog;
		validateName();
	}

	public Game(final String name, final String commandLog) {
		this.name = name;
		this.commandLog = commandLog;
		validateName();
	}

	private void validateName() {
		if (name.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_NAME_ERROR);
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
