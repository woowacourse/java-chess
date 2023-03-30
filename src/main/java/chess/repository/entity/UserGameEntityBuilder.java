package chess.repository.entity;

public class UserGameEntityBuilder {

	private long id;
	private long userId;
	private long gameId;

	public UserGameEntityBuilder id(final long id) {
		this.id = id;
		return this;
	}

	public UserGameEntityBuilder userId(final long userId) {
		this.userId = userId;
		return this;
	}

	public UserGameEntityBuilder gameId(final long gameId) {
		this.gameId = gameId;
		return this;
	}

	public UserGameEntity build() {
		return new UserGameEntity(id, userId, gameId);
	}
}
