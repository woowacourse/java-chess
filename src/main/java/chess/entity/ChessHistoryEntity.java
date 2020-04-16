package chess.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Objects;

import chess.domain.chessGame.ChessCommand;

public class ChessHistoryEntity extends BaseEntity {

	private static final long DEFAULT_HISTORY_ID = 0L;
	private static final long DEFAULT_GAME_ID = 0L;
	private static final String MOVE_COMMAND = "move";

	private final long historyId;
	private final long gameId;
	private final String start;
	private final String end;

	private ChessHistoryEntity(final long historyId, final long gameId, final String start, final String end,
		final LocalDateTime createdTime) {
		super(createdTime);
		validate(start, end);
		this.historyId = historyId;
		this.gameId = gameId;
		this.start = start;
		this.end = end;
	}

	public static ChessHistoryEntity of(final long historyId, final long gameId, final String start, final String end,
		final LocalDateTime createdTime) {
		return new ChessHistoryEntity(historyId, gameId, start, end, createdTime);
	}

	public static ChessHistoryEntity of(final long gameId, final String start, final String end,
		final LocalDateTime createdTime) {
		return new ChessHistoryEntity(DEFAULT_HISTORY_ID, gameId, start, end, createdTime);
	}

	public static ChessHistoryEntity of(final String start, final String end) {
		return new ChessHistoryEntity(DEFAULT_HISTORY_ID, DEFAULT_GAME_ID, start, end,
			LocalDateTime.now(ZoneId.of("Asia/Seoul")));
	}

	public static ChessHistoryEntity of(final long historyId, final ChessHistoryEntity entity) {
		Objects.requireNonNull(entity, "엔티티가 null입니다.");
		return new ChessHistoryEntity(historyId, entity.gameId, entity.start, entity.end, entity.createdTime);
	}

	private void validate(final String start, final String end) {
		Objects.requireNonNull(start, "출발 위치가 null입니다.");
		Objects.requireNonNull(end, "도착 위치가 null입니다.");
	}

	public ChessCommand generateMoveCommand() {
		return ChessCommand.of(Arrays.asList(MOVE_COMMAND, start, end));
	}

	public long getHistoryId() {
		return historyId;
	}

	public long getGameId() {
		return gameId;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

}
