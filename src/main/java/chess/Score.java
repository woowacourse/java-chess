package chess;

public class Score {
	private final int score;

	public Score(final int score) {
		this.score = score;
	}

	public Result compare(final Score score) {
		if (this.score > score.score) {
			return Result.WIN;
		}
		if (this.score < score.score) {
			return Result.LOSE;
		}
		return Result.DRAW;
	}
}
