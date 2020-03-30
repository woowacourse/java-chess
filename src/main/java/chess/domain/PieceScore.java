package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Piece;

public enum PieceScore {
	KING(0),
	QUEEN(9),
	ROOK(5),
	BISHOP(3),
	KNIGHT(2.5),
	PAWN(0.5);

	public static final int PAWN_COUNT = 1;
	public static final int PAWN_BASIC_SCORE = 1;

	private final double score;

	PieceScore(double score) {
		this.score = score;
	}

	private static PieceScore of(Piece piece) {
		return Arrays.stream(PieceScore.values())
			.filter(piece::isSameName)
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("해당하는 Piece가 Null입니다!"));
	}

	public static double calculateByColor(Map<Position, Piece> pieces, Color color) {
		double teamScore = 0;

		for (File file : File.values()) {
			teamScore += sumPieceScoreExceptPawn(color, file, pieces);
			teamScore += sumPawnScore(pawnsOfColumn(color, file, pieces));
		}

		return teamScore;
	}

	private static List<Piece> pawnsOfColumn(Color color, File file, Map<Position, Piece> pieces) {
		return Arrays.stream(Rank.values())
			.map(rank -> pieces.get(Position.of(file, rank)))
			.filter(Objects::nonNull)
			.filter(piece -> piece.isSameColor(color))
			.filter(Piece::isPawn)
			.collect(Collectors.toList());

	}

	private static double sumPieceScoreExceptPawn(Color color, File file, Map<Position, Piece> pieces) {
		return Arrays.stream(Rank.values())
			.map(rank -> pieces.get(Position.of(file, rank)))
			.filter(Objects::nonNull)
			.filter(piece -> piece.isSameColor(color))
			.filter(piece -> !piece.isPawn())
			.mapToDouble(PieceScore::calculateScore)
			.sum();
	}

	private static double calculateScore(Piece piece) {
		return of(piece).score;
	}

	private static double sumPawnScore(List<Piece> pawns) {
		if (pawns.size() == PAWN_COUNT) {
			return PAWN_BASIC_SCORE;
		}

		return pawns.stream()
			.mapToDouble(pawn -> of(pawn).score)
			.sum();
	}
}
