package view;

import domain.pieces.Pieces;
import domain.team.Team;

public class Announcement {
	private static final String NEW_LINE = "<br/>";

	private final String message;

	private Announcement(final String message) {
		this.message = message;
	}

	public static Announcement ofStatus(final Pieces pieces) {
		final Team winner = pieces.computeWinner();
		final Announcement whoWinAnnouncement = ofWinner(winner);

		if (pieces.isKingKilled()) {
			return whoWinAnnouncement;
		}

		return whoWinAnnouncement.joinWithBr(ofScores(pieces));
	}

	private static Announcement ofWinner(final Team team) {
		if (Team.BLACK == team) {
			return new Announcement("검은 팀의 승리입니다.");
		}
		if (Team.WHITE == team) {
			return new Announcement("흰 팀의 승리입니다.");
		}
		return new Announcement("비겼습니다.");
	}

	private static Announcement ofScores(final Pieces pieces) {
		double blackScore = pieces.sumTeamScore(Team.BLACK);
		double whiteScore = pieces.sumTeamScore(Team.WHITE);

		final String announcement = String.format("검은 팀의 점수는 %f 입니다."
				+ NEW_LINE
				+ "흰 팀의 점수는 %f 입니다.", blackScore, whiteScore);

		return new Announcement(announcement);
	}

	private Announcement joinWithBr(final Announcement announcement) {
		return new Announcement(this.message + NEW_LINE + announcement.message);
	}

	public static Announcement ofFirst() {
		return new Announcement("게임을 시작하려면 start를 눌러주세요.");
	}

	public static Announcement ofPlaying() {
		return new Announcement("게임 중 입니다...");
	}

	public static Announcement ofEnd() {
		return new Announcement("게임이 종료되었습니다."
				+ NEW_LINE
				+ "정보를 확인하려면 status, 다시 시작하려면 start를 입력해주세요.");
	}

	public static Announcement of(String string) {
		return new Announcement(string);
	}

	public String getString() {
		return message;
	}
}
