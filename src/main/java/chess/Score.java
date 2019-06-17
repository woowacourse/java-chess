package chess;

import java.util.Objects;

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

	public Score add(Score score) {
		return new Score(this.score + score.score);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Score)) {
			return false;
		}
		final Score score1 = (Score) o;
		return score == score1.score;
	}

	@Override
	public int hashCode() {
		return Objects.hash(score);
	}
}
