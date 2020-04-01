package chess.result;

import java.util.Arrays;

public enum GameResult {
	WIN(1, "승리"),
	DRAW(0, "무승부"),
	LOSE(-1, "패배");

	private final int compareValue;
	private final String message;

	GameResult(int compareValue, String message) {
		this.compareValue = compareValue;
		this.message = message;
	}

	public static GameResult findResult(Score score, Score other) {
		int compare = score.compare(other);
		return Arrays.stream(values())
			.filter(gameResult -> gameResult.compareValue == compare)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("잘못된 승패 입력입니다."));
	}

	public String getMessage() {
		return message;
	}
}
