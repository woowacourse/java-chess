package chess.domain;

import chess.domain.chessPiece.piece.Pawn;
import chess.domain.chessPiece.piece.Piece;
import chess.domain.chessPiece.piece.PieceAbility;
import chess.domain.chessPiece.position.File;

import java.util.List;
import java.util.stream.Collectors;

public class Result {
	private static final int ZERO = 0;
	private static final int ONE_PAWN_COUNT = 1;
	private static final double ONE_PAWN_BONUS = 0.5;

	private final double blackTeamScore;
	private final double whiteTeamScore;

	public Result(List<Piece> blackTeam, List<Piece> whiteTeam) {
		this.blackTeamScore = calculateTeamScore(blackTeam);
		this.whiteTeamScore = calculateTeamScore(whiteTeam);
	}

	public static double calculateTeamScore(List<Piece> team) {
		double result = ZERO;
		for (File file : File.values()) {
			List<Piece> piecesInOneFile = team.stream()
					.filter(x -> x.isSameFile(file))
					.collect(Collectors.toList());
			result += piecesInOneFile.stream()
					.mapToDouble(PieceAbility::getScore)
					.sum();
			result += OnePawnBonus(piecesInOneFile);
		}
		return result;
	}

	private static double OnePawnBonus(List<Piece> piecesInOneFile) {
		long pawnCount = piecesInOneFile.stream().filter(x -> x instanceof Pawn).count();
		if (pawnCount == ONE_PAWN_COUNT) {
			return ONE_PAWN_BONUS;
		}
		return ZERO;
	}

	public double getBlackTeamScore() {
		return blackTeamScore;
	}

	public double getWhiteTeamScore() {
		return whiteTeamScore;
	}
}
