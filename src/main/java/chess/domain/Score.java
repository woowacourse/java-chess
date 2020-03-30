package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Knight;
import chess.domain.chesspiece.Pawn;
import chess.domain.chesspiece.Queen;
import chess.domain.chesspiece.Rook;

public enum Score {
	QUEEN_SCORE(9.0, chessPiece -> chessPiece.getClass() == Queen.class),
	ROOK_SCORE(5.0, chessPiece -> chessPiece.getClass() == Rook.class),
	BISHOP_SCORE(3.0, chessPiece -> chessPiece.getClass() == Bishop.class),
	KNIGHT_SCORE(2.5, chessPiece -> chessPiece.getClass() == Knight.class),
	PAWN_SCORE(1.0, chessPiece -> chessPiece.getClass() == Pawn.class),
	SAME_COLUMN_PAWN_SCORE(0.5, chessPiece -> false),
	ETC_SCORE(0, chessPiece -> true);

	private static final String NOT_MATCH_MESSAGE = "해당 점수를 찾을 수 없습니다.";
	private static final String NOT_HAVE_SCORE_MESSAGE = "더할 숫자가 없습니다.";

	private final double score;
	private final Predicate<ChessPiece> predicate;

	Score(double score, Predicate<ChessPiece> predicate) {
		this.score = score;
		this.predicate = predicate;
	}

	public static Score of(ChessPiece chessPiece) {
		return Arrays.stream(values())
			.filter(val -> val.predicate.test(chessPiece))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException(NOT_MATCH_MESSAGE));
	}

	public static double sum(List<Score> scores) {
		return scores.stream()
			.map(Score::getScore)
			.reduce(Double::sum)
			.orElseThrow(() -> new IllegalArgumentException(NOT_HAVE_SCORE_MESSAGE));
	}

	public static double calculateDuplicatePawnScore(long count) {
		return SAME_COLUMN_PAWN_SCORE.score * count;
	}

	private double getScore() {
		return score;
	}
}
