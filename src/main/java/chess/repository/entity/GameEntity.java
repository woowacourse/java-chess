package chess.repository.entity;

public class GameEntity implements Entity {

	private final long id;

	public GameEntity(final Long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
}
