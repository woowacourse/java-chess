package chess.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class ChessGameEntity extends BaseEntity {

	private static final long DEFAULT_GAME_ID = 0L;

	private final long gameId;

	private ChessGameEntity(final long gameId, final LocalDateTime createdTime) {
		super(createdTime);
		this.gameId = gameId;
	}

	public static ChessGameEntity of(final long gameId, final LocalDateTime createdTime) {
		return new ChessGameEntity(gameId, createdTime);
	}

	public static ChessGameEntity of(final long gameId, final ChessGameEntity entity) {
		Objects.requireNonNull(entity, "엔티티가 null입니다.");
		return new ChessGameEntity(gameId, entity.createdTime);
	}

	public static ChessGameEntity of(final LocalDateTime createdTime) {
		return new ChessGameEntity(DEFAULT_GAME_ID, createdTime);
	}

	public long getGameId() {
		return gameId;
	}

}
