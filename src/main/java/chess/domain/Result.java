package chess.domain;

public class Result {
	private static final String BLACK_TEAM_WINNING = "Black진영이 이기고 있습니다!";
	private static final String WHITE_TEAM_WINNING = "White진영이 이기고 있습니다!";
	private static final String DRAWING = "비기고 있습니다!";

	private final double blackTeamScore;
	private final double whiteTeamScore;
	private final String winTeam;

	public Result(double blackTeamScore, double whiteTeamScore) {
		this.blackTeamScore = blackTeamScore;
		this.whiteTeamScore = whiteTeamScore;
		this.winTeam = calculateWinTeam(blackTeamScore, whiteTeamScore);
	}

	public static String calculateWinTeam(double blackTeamScore, double whiteTeamScore) {
		if (blackTeamScore > whiteTeamScore) {
			return BLACK_TEAM_WINNING;
		} else if (whiteTeamScore > blackTeamScore) {
			return WHITE_TEAM_WINNING;
		}
		return DRAWING;
	}

	public double getBlackTeamScore() {
		return blackTeamScore;
	}

	public double getWhiteTeamScore() {
		return whiteTeamScore;
	}

	public String getWinTeam() {
		return winTeam;
	}
}
