package chess.domain;

public class Result {
	private final String WinTeam;
	private final double score;

	public Result(String winTeam, double score) {
		WinTeam = winTeam;
		this.score = score;
	}

	public String getWinTeam() {
		return WinTeam;
	}

	public double getScore() {
		return score;
	}
}
