package chess.domain.game;

public class Game {

	private final int id;
	private final String name;
	private final String commandLog;

	public Game(final int id, final String name, final String commandLog) {
		this.id = id;
		this.name = name;
		this.commandLog = commandLog;
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
