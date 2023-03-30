package chess.repository.entity;

public class UserGameEntity {

	private long id;
	private long userId;
	private long gameId;

	public UserGameEntity(final long id, final long userId, final long gameId) {
		this.id = id;
		this.userId = userId;
		this.gameId = gameId;
	}

	public long getId() {
		return id;
	}

	public long getUserId() {
		return userId;
	}

	public long getGameId() {
		return gameId;
	}

	@Override
	public String toString() {
		return "UserGameEntity{" +
			"id=" + id +
			", userId=" + userId +
			", gameId=" + gameId +
			'}';
	}
}
