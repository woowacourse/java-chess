package chess.view.dto.responsedto;

import java.util.Objects;

public class ScoreDTO {
	private final String team;
	private final double score;

	public ScoreDTO(String team, double score) {
		this.team = team;
		this.score = score;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ScoreDTO scoreDTO = (ScoreDTO)o;
		return Double.compare(scoreDTO.score, score) == 0 &&
			Objects.equals(team, scoreDTO.team);
	}

	@Override
	public int hashCode() {
		return Objects.hash(team, score);
	}

	@Override
	public String toString() {
		return "ScoreDTO{" +
			"team='" + team + '\'' +
			", score=" + score +
			'}';
	}
}
