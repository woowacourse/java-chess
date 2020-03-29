package chess.domain.chessGame;

import static java.util.stream.Collectors.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

import chess.domain.chessPiece.ChessPiece;
import chess.domain.chessPiece.pieceType.Pawn;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.ChessFile;
import chess.domain.position.Position;

public class ChessStatus {

	private static final Double PAWN_ALONE_ON_FILE_SCORE = 1.;

	private final Map<PieceColor, Double> chessStatus;

	private ChessStatus(Map<PieceColor, Double> chessStatus) {
		this.chessStatus = chessStatus;
	}

	public static ChessStatus of(Map<Position, ChessPiece> chessBoard) {
		validate(chessBoard);
		return Arrays.stream(PieceColor.values())
			.collect(collectingAndThen(
				toMap(
					Function.identity(),
					pieceColor -> calculateStatusOf(chessBoard, pieceColor)),
				ChessStatus::new));
	}

	private static void validate(Map<Position, ChessPiece> chessBoard) {
		if (Objects.isNull(chessBoard) || chessBoard.isEmpty()) {
			throw new IllegalArgumentException("체스 보드가 존재하지 않습니다.");
		}
	}

	private static double calculateStatusOf(Map<Position, ChessPiece> chessBoard, PieceColor pieceColor) {
		return Arrays.stream(ChessFile.values())
			.map(chessFile -> findChessPieceOn(chessFile, pieceColor, chessBoard))
			.mapToDouble(ChessStatus::calculateScoreOf)
			.sum();
	}

	private static Stream<ChessPiece> findChessPieceOn(ChessFile chessFile, PieceColor pieceColor,
		Map<Position, ChessPiece> chessBoard) {
		return chessBoard.entrySet().stream()
			.filter(entry -> entry.getKey().isSame(chessFile))
			.map(Map.Entry::getValue)
			.filter(chessPiece -> chessPiece.isSame(pieceColor));
	}

	private static double calculateScoreOf(Stream<ChessPiece> chessPieces) {
		final boolean pawnKey = true;
		final boolean notPawnKey = false;

		Map<Boolean, Double> partitioningByPawn = chessPieces.collect(
			partitioningBy(
				chessPiece -> chessPiece instanceof Pawn,
				summingDouble(ChessPiece::getScore)));

		if (partitioningByPawn.get(pawnKey) > PAWN_ALONE_ON_FILE_SCORE) {
			return (partitioningByPawn.get(pawnKey) / 2) + partitioningByPawn.get(notPawnKey);
		}
		return partitioningByPawn.get(pawnKey) + partitioningByPawn.get(notPawnKey);
	}

	public double getStatusOf(PieceColor pieceColor) {
		return chessStatus.get(pieceColor);
	}

}
