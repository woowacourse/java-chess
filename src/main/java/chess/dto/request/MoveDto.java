package chess.dto.request;

public class MoveDto {

	private final int gameId;
	private final String source;
	private final String target;

	public MoveDto(final int gameId, final String source, final String target) {
		this.gameId = gameId;
		this.source = source;
		this.target = target;
	}

	public int getGameId() {
		return gameId;
	}

	public String getSource() {
		return source;
	}

	public String getTarget() {
		return target;
	}
}
