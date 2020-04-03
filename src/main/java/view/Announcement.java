package view;

import view.board.Board;
import view.board.RowOfBoard;
import domain.pieces.Pieces;
import domain.team.Team;

public class Announcement {
	private static final String NEW_LINE = System.lineSeparator();

	public static void printStart() {
		System.out.println("체스 게임을 시작합니다.");
		System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
	}

	public static void printBoard(Board board) {
		StringBuilder stringBuilder = new StringBuilder();
		for (RowOfBoard row : board.getBoard()) {
			stringBuilder.append(String.join("", row.getRowOfBoard()))
					.append(NEW_LINE);
		}

		System.out.println(stringBuilder.toString());
	}

	public static String getStatusAnnouncement(Pieces pieces) {
		if (pieces.isKingKilled()) {
			return getWhoWinStringWhenKingKilled(pieces);
		}

		double blackScore = pieces.sumTeamScore(Team.BLACK);
		double whiteScore = pieces.sumTeamScore(Team.WHITE);

		return getScoreStrings(blackScore, whiteScore)
				+ getWhoWinStringWhenKingNotKilled(blackScore, whiteScore);
	}

	private static String getWhoWinStringWhenKingKilled(Pieces pieces) {
		if (pieces.isKingKilled(Team.BLACK)) {
			return "흰 팀의 승리입니다.";
		}
		return "검은 팀의 승리입니다.";
	}

	private static String getScoreStrings(double blackScore, double whiteScore) {
		return String.format("검은색 팀의 점수는 %f 입니다." + NEW_LINE, blackScore)
				+ String.format("흰색 팀의 점수는 %f 입니다." + NEW_LINE, whiteScore);
	}

	private static String getWhoWinStringWhenKingNotKilled(double blackScore, double whiteScore) {
		if (blackScore > whiteScore) {
			return "검은색 팀의 승리입니다.";
		}
		if (blackScore < whiteScore) {
			return "흰색 팀의 승리입니다.";
		}
		return "비겼습니다.";
	}
}
