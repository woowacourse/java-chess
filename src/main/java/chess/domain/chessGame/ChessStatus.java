package chess.domain.chessGame;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.ChessFile;
import chess.domain.position.Position;

public class ChessStatus {

	private static final double PAWN_ALONE_ON_FILE_SCORE = 1.;
	private static final double PAWN_EXIST_SAME_FILE_CONSTANT = 0.5;

	private final Map<PieceColor, Double> chessStatus;

	private ChessStatus(final Map<PieceColor, Double> chessStatus) {
		this.chessStatus = chessStatus;
	}

	public static ChessStatus of(final Map<Position, ChessPiece> chessBoard) {
		validate(chessBoard);

		return Arrays.stream(PieceColor.values())
			.collect(collectingAndThen(
				toMap(
					Function.identity(),
					pieceColor -> calculateStatusOf(chessBoard, pieceColor)),
				ChessStatus::new));
	}

	private static void validate(final Map<Position, ChessPiece> chessBoard) {
		if (Objects.isNull(chessBoard) || chessBoard.isEmpty()) {
			throw new IllegalArgumentException("체스 보드가 존재하지 않습니다.");
		}
	}

	private static double calculateStatusOf(final Map<Position, ChessPiece> chessBoard, PieceColor pieceColor) {
		return Arrays.stream(ChessFile.values())
			.map(chessFile -> findChessPieceOn(chessFile, pieceColor, chessBoard))
			.mapToDouble(ChessStatus::calculateScoreOf)
			.sum();
	}

	private static Stream<ChessPiece> findChessPieceOn(final ChessFile chessFile, final PieceColor pieceColor,
		final Map<Position, ChessPiece> chessBoard) {
		return chessBoard.entrySet().stream()
			.filter(entry -> entry.getKey().isSame(chessFile))
			.map(Map.Entry::getValue)
			.filter(chessPiece -> chessPiece.isSame(pieceColor));
	}

	private static double calculateScoreOf(final Stream<ChessPiece> chessPieces) {
		final boolean pawnKey = true;
		final boolean notPawnKey = false;

		final Map<Boolean, Double> partitioningByPawn = chessPieces.collect(
			partitioningBy(
				ChessPiece::isPawn,
				summingDouble(ChessPiece::getScore)));

		return calculatePawnScore(partitioningByPawn.get(pawnKey)) + partitioningByPawn.get(notPawnKey);
	}

	private static double calculatePawnScore(final double pawnTotalScore) {
		if (pawnTotalScore > PAWN_ALONE_ON_FILE_SCORE) {
			return pawnTotalScore * PAWN_EXIST_SAME_FILE_CONSTANT;
		}
		return pawnTotalScore;
	}

	public double getStatusOf(final PieceColor pieceColor) {
		return chessStatus.get(pieceColor);
	}

	public Map<PieceColor, Double> getChessStatus() {
		return chessStatus;
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		final ChessStatus that = (ChessStatus)object;
		return Objects.equals(chessStatus, that.chessStatus);
	}

	@Override
	public int hashCode() {
		return Objects.hash(chessStatus);
	}

}
