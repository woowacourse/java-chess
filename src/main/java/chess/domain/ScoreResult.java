package chess.domain;

import chess.domain.board.position.Row;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScoreResult {
	public static Map<Color, Double> calculateScore(List<Piece> pieces) {
		Map<Color, Double> scores = new HashMap<>();
		List<Piece> white = pieces.stream()
				.filter(Piece::isWhite)
				.collect(Collectors.toList());

		double whiteScore = white.stream()
				.mapToDouble(Piece::getScore)
				.sum();

		List<Piece> whitePawns = white.stream()
				.filter(Piece::isPawn)
				.collect(Collectors.toList());

		int totalSameRowCount = 0;
		for (Row row : Row.values()) {
			int sameRowCount = 0;
			for (Piece pawn : whitePawns) {
				if (row.isSame(pawn.getPosition().getRow())) {
					sameRowCount++;
				}
			}
			if (sameRowCount > 1) {
				totalSameRowCount += sameRowCount;
			}

		}

		whiteScore -= (0.5 * totalSameRowCount);
		scores.put(Color.WHITE, whiteScore);


		List<Piece> black = pieces.stream()
				.filter(Piece::isBlack)
				.collect(Collectors.toList());

		double blackScore = black.stream()
				.mapToDouble(Piece::getScore)
				.sum();

		List<Piece> blackPawns = black.stream()
				.filter(Piece::isPawn)
				.collect(Collectors.toList());

		totalSameRowCount = 0;
		for (Row row : Row.values()) {
			int sameRowCount = 0;
			for (Piece pawn : blackPawns) {
				if (row.isSame(pawn.getPosition().getRow())) {
					sameRowCount++;
				}
			}
			if (sameRowCount > 1) {
				totalSameRowCount += sameRowCount;
			}
		}

		blackScore -= (0.5 * totalSameRowCount);
		scores.put(Color.BLACK, blackScore);
		return Collections.unmodifiableMap(scores);
	}
}
