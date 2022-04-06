package chess.domain.game;

public class Game {

	private final int id;
	private final String name;

	public Game(final int id, final String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
