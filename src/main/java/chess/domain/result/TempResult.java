package chess.domain.result;

import java.util.Map;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Piece;

public class TempResult implements Result {

	private final Score whiteScore;
	private final Score blackScore;

	private TempResult(final Score whiteScore, final Score blackScore) {
		this.whiteScore = whiteScore;
		this.blackScore = blackScore;
	}

	public static TempResult from(final Map<Position, Piece> board) {
		Score whiteScore = Score.of(Team.WHITE, board);
		Score blackScore = Score.of(Team.BLACK, board);
		return new TempResult(whiteScore, blackScore);
	}

	@Override
	public Team getWinner() {
		int whiteScore = getWhiteScoreMultipliedBy10();
		int blackScore = getBlackScoreMultipliedBy10();
		if (whiteScore > blackScore) {
			return Team.WHITE;
		}
		if (whiteScore < blackScore) {
			return Team.BLACK;
		}
		return Team.EMPTY;
	}

	@Override
	public int getWhiteScoreMultipliedBy10() {
		return whiteScore.getScoreMultipliedBy10();
	}

	@Override
	public int getBlackScoreMultipliedBy10() {
		return blackScore.getScoreMultipliedBy10();
	}
}
