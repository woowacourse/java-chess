package chess.domain.result;

import java.util.Map;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Piece;

public class ScoreAndWinnerResult {

	private final Score whiteScore;
	private final Score blackScore;

	private ScoreAndWinnerResult(final Score whiteScore, final Score blackScore) {
		this.whiteScore = whiteScore;
		this.blackScore = blackScore;
	}

	public static ScoreAndWinnerResult from(final Map<Position, Piece> board) {
		Score whiteScore = Score.of(Team.WHITE, board);
		Score blackScore = Score.of(Team.BLACK, board);
		return new ScoreAndWinnerResult(whiteScore, blackScore);
	}

	public Team getWinner() {
		int white = whiteScore.getScoreMultipliedBy10();
		int black = blackScore.getScoreMultipliedBy10();
		if (white > black) {
			return Team.WHITE;
		}
		if (white < black) {
			return Team.BLACK;
		}
		return Team.EMPTY;
	}

	public int getWhiteScoreMultipliedBy10() {
		return whiteScore.getScoreMultipliedBy10();
	}

	public int getBlackScoreMultipliedBy10() {
		return blackScore.getScoreMultipliedBy10();
	}
}
