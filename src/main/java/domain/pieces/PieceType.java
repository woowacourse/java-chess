package domain.pieces;

import java.util.Arrays;

public enum PieceType {
	BISHOP(3, "B", "bishop"),
	KING(0, "K", "king"),
	KNIGHT(2.5, "N", "knight"),
	PAWN(2, "P", "pawn"),
	QUEEN(9, "Q", "queen"),
	ROOK(5, "R", "rook");

	public static final int HALF_DIVIDER = 2;

	private final double score;
	private final String initial;
	private final String name;

	PieceType(double score, String initial, String name) {
		this.score = score;
		this.initial = initial;
		this.name = name;
	}

	public static PieceType ofName(String name) {
		return Arrays.stream(values())
				.filter(pieceType -> pieceType.name.equals(name))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	public static double getPawnHalfScore() {
		return PAWN.score / HALF_DIVIDER;
	}

	public double getScore() {
		return score;
	}

	public String getInitial() {
		return initial;
	}

	public String getName() {
		return name;
	}
}
