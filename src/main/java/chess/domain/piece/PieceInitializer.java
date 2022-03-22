package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class PieceInitializer {

	public static List<Piece> generate() {
		List<Piece> pieces = new ArrayList<>();
		pieces.addAll(generateKings());
		pieces.addAll(generateQueens());
		pieces.addAll(generateBishops());
		pieces.addAll(generateKnights());
		pieces.addAll(generateRooks());
		pieces.addAll(generatePawns());
		return pieces;
	}

	private static List<King> generateKings() {
		return List.of(
			King.createWhite(1, 5),
			King.createBlack(8, 5)
		);
	}

	private static List<Queen> generateQueens() {
		return List.of(
			Queen.createWhite(1, 4),
			Queen.createBlack(8, 4)
		);
	}

	private static List<Bishop> generateBishops() {
		return List.of(
			Bishop.createWhite(1, 3),
			Bishop.createWhite(1, 6),
			Bishop.createBlack(8, 3),
			Bishop.createBlack(8, 6)
		);
	}

	private static List<Knight> generateKnights() {
		return List.of(
			Knight.createWhite(1, 2),
			Knight.createWhite(1, 7),
			Knight.createBlack(8, 2),
			Knight.createBlack(8, 7)
		);
	}

	private static List<Rook> generateRooks() {
		return List.of(
			Rook.createWhite(1, 1),
			Rook.createWhite(1, 8),
			Rook.createBlack(8, 1),
			Rook.createBlack(8, 8)
		);
	}

	private static List<Pawn> generatePawns() {
		List<Pawn> pawns = new ArrayList<>();
		for (int i = 1; i <= 8; i++) {
			pawns.add(Pawn.createWhite(2, i));
			pawns.add(Pawn.createBlack(7, i));
		}
		return pawns;
	}
}
