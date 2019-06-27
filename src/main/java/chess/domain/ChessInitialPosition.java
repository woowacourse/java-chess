package chess.domain;

import java.util.*;

public class ChessInitialPosition {
	private static Map<ChessPiece, List<Position>> initialPositions = new HashMap<>();
	static {
		initializePawn(ChessPiece.BLACK_PAWN, 7);
		initializePawn(ChessPiece.WHITE_PAWN, 2);

		initializeRookPosition(ChessPiece.BLACK_ROOK, 8);
		initializeRookPosition(ChessPiece.WHITE_ROOK, 1);

		initializeKnightPosition(ChessPiece.BLACK_KNIGHT, 8);
		initializeKnightPosition(ChessPiece.WHITE_KNIGHT, 1);

		initializeBishopPosition(ChessPiece.BLACK_BISHOP, 8);
		initializeBishopPosition(ChessPiece.WHITE_BISHOP, 1);

		initializeQueenPosition(ChessPiece.BLACK_QUEEN, 8);
		initializeQueenPosition(ChessPiece.WHITE_QUEEN, 1);

		initializeKingPosition(ChessPiece.BLACK_KING, 8);
		initializeKingPosition(ChessPiece.WHITE_KING, 1);
	}

	public static void initializePawn(ChessPiece pawn, int coordinateY) {
		List<Position> position = new ArrayList<>();
		for (int i = 1; i <= 8; ++i) {
			position.add(Position.getPosition(i, coordinateY));
		}
		initialPositions.put(pawn, position);
	}

	private static void initializeRookPosition(ChessPiece rook, int coordinateY) {
		initialPositions.put(rook, Arrays.asList(
				Position.getPosition(1, coordinateY), Position.getPosition(8, coordinateY)
		));
	}

	private static void initializeKnightPosition(ChessPiece knight, int coordinateY) {
		initialPositions.put(knight, Arrays.asList(
				Position.getPosition(2, coordinateY), Position.getPosition(7, coordinateY)));
	}

	private static void initializeBishopPosition(ChessPiece bishop, int coordinateY) {
		initialPositions.put(bishop, Arrays.asList(
				Position.getPosition(3, coordinateY), Position.getPosition(6, coordinateY)));
	}

	private static void initializeQueenPosition(ChessPiece queen, int coordinateY) {
		initialPositions.put(queen, Arrays.asList(Position.getPosition(4, coordinateY)));
	}

	private static void initializeKingPosition(ChessPiece king, int coordinateY) {
		initialPositions.put(king, Arrays.asList(Position.getPosition(5, coordinateY)));
	}

	public Map<ChessPiece, List<Position>> getPositions() {
		return initialPositions;
	}
}
