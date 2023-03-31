package chess.domain.result;

import java.util.HashMap;
import java.util.Map;

import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class Score {

	private final int scoreMultipliedBy10;

	private Score(int scoreMultipliedBy10) {
		this.scoreMultipliedBy10 = scoreMultipliedBy10;
	}

	public static Score of(Team team, Map<Position, Piece> board) {
		Map<Integer, Integer> pawnColumnsToCount = new HashMap<>();
		for (int i = 0; i < 8; i++) {
			pawnColumnsToCount.put(i, 0);
		}
		return new Score(calculateScoreMultipliedBy10(team, board, pawnColumnsToCount));
	}

	private static int calculateScoreMultipliedBy10(
		final Team team,
		final Map<Position, Piece> board,
		final Map<Integer, Integer> pawnCountsByColumn
	) {
		int score = 0;
		for (Position position : board.keySet()) {
			Piece piece = board.get(position);
			score += calculateScoreIncrement(team, pawnCountsByColumn, position, piece);
		}
		return score;
	}

	private static int calculateScoreIncrement(final Team team,
		final Map<Integer, Integer> pawnCountsByColumn,
		final Position position,
		final Piece piece
	) {
		boolean isPieceGivenTeam = piece.isGivenTeam(team);
		if (isPieceGivenTeam && isPiecePawn(piece)) {
			return getPawnScoreIncrement(position, pawnCountsByColumn);
		}
		if (isPieceGivenTeam) {
			return PieceType.getScoreMultipliedBy10(piece.getPieceType());
		}
		return 0;
	}

	private static boolean isPiecePawn(final Piece piece) {
		return piece.isGivenType(PieceType.PAWN) || piece.isGivenType(PieceType.INITIAL_PAWN);
	}

	private static int getPawnScoreIncrement(final Position position, final Map<Integer, Integer> pawnCountsByColumn) {
		int column = position.getColumn();
		int pawnCount = pawnCountsByColumn.get(column);
		increasePawnCount(pawnCountsByColumn, column);
		if (pawnCount == 0) {
			return PieceType.getScoreMultipliedBy10(PieceType.PAWN);
		}
		if (pawnCount == 1) {
			return 0;
		}
		return PieceType.getScoreMultipliedBy10(PieceType.PAWN) / 2;
	}

	private static void increasePawnCount(final Map<Integer, Integer> pawnCountsByColumn, final int column) {
		pawnCountsByColumn.put(column, pawnCountsByColumn.get(column) + 1);
	}

	public int getScoreMultipliedBy10() {
		return scoreMultipliedBy10;
	}
}
