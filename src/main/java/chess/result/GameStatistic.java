package chess.result;

import java.util.Objects;

import chess.team.Team;

public class GameStatistic {
	private final Team team;
	private final Score score;
	private final GameResult gameResult;

	public GameStatistic(Team team, Score score, GameResult gameResult) {
		Objects.requireNonNull(score, "스코어의 정보가 없습니다.");
		this.team = team;
		this.score = score;
		this.gameResult = gameResult;
	}

	public Team getTeam() {
		return team;
	}

	public Score getScore() {
		return score;
	}

	public GameResult getGameResult() {
		return gameResult;
	}
}
