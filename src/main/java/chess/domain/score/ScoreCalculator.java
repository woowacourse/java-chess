package chess.domain.score;

import static chess.domain.board.Position.MAX_POSITION;
import static chess.domain.board.Position.MIN_POSITION;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.List;
import java.util.stream.IntStream;

public class ScoreCalculator {

	private static final int SAME_COLUMN_PAWN_COUNT = 2;
	private static final double DUPLICATION_PAWN_SCORE = 0.5;

	public double calculate(final Board board, final Team team) {
		return IntStream.rangeClosed(MIN_POSITION, MAX_POSITION)
				.mapToObj(column -> board.getAllyPiecesByColumn(team, column))
				.mapToDouble(this::calculateColumnScore)
				.sum();
	}

	private double calculateColumnScore(final List<Piece> columnPieces) {
		double sum = columnPieces.stream()
				.mapToDouble(Piece::getScore)
				.sum();

		return subtractDuplicationPawnScore(columnPieces, sum);
	}

	private double subtractDuplicationPawnScore(final List<Piece> columnPieces, final double sum) {
		long pawnCount = columnPieces.stream()
				.filter(Piece::isPawn)
				.count();

		if (pawnCount >= SAME_COLUMN_PAWN_COUNT) {
			return sum - DUPLICATION_PAWN_SCORE * pawnCount;
		}
		return sum;
	}
}
