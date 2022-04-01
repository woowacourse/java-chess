package chess.domain.score;

import chess.domain.board.Board;
import chess.domain.board.File;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import java.util.Arrays;
import java.util.List;

public class ScoreCalculator {

	private static final int SAME_FILE_PAWN_COUNT = 2;
	private static final double DUPLICATION_PAWN_SCORE = 0.5;

	public double calculate(final Board board, final Team team) {
		return Arrays.stream(File.values())
				.map(file -> board.getAllyPiecesByFile(team, file))
				.mapToDouble(this::calculateFileScore)
				.sum();
	}

	private double calculateFileScore(final List<Piece> filePieces) {
		double sum = filePieces.stream()
				.mapToDouble(Piece::getScore)
				.sum();

		return subtractDuplicationPawnScore(filePieces, sum);
	}

	private double subtractDuplicationPawnScore(final List<Piece> filePieces, final double sum) {
		long pawnCount = filePieces.stream()
				.filter(Piece::isPawn)
				.count();

		if (pawnCount >= SAME_FILE_PAWN_COUNT) {
			return sum - DUPLICATION_PAWN_SCORE * pawnCount;
		}
		return sum;
	}
}
