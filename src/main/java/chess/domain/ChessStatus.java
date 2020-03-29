package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Column;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ChessStatus {
	private static final int UNIQUE = 1;
	private List<Piece> pieces;

	public ChessStatus(List<Piece> pieces) {
		validateNullAndEmpty(pieces);
		this.pieces = pieces;
	}

	private void validateNullAndEmpty(List<Piece> pieces) {
		if (Objects.isNull(pieces) || pieces.isEmpty()) {
			throw new IllegalArgumentException("체스판엔 null이나 빈 값이 올 수 없습니다.");
		}
	}

	public double calculateScore(Side side) {
		return calculateRawScore(side) + countUniquePawnInColumn(side) * Pawn.SCORE;
	}

	private double calculateRawScore(Side side) {
		return pieces.stream()
				.filter(piece -> piece.isSameSide(side))
				.mapToDouble(Piece::getScore)
				.sum();
	}

	private double countUniquePawnInColumn(Side side) {
		return Arrays.stream(Column.values())
				.mapToLong(col -> countPawnInColumn(col, side))
				.filter(i -> i == UNIQUE)
				.count();
	}

	private long countPawnInColumn(Column column, Side side) {
		return pieces.stream()
				.filter(piece -> piece instanceof Pawn && piece.isSameSide(side) && piece.isSameCol(column))
				.count();
	}
}
