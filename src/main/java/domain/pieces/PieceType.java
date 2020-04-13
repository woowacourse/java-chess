package domain.pieces;

import domain.coordinate.Coordinate;
import domain.team.Team;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceType {
	BISHOP(3, "B", "bishop", Bishop::new),
	KING(0, "K", "king", King::new),
	KNIGHT(2.5, "N", "knight", Knight::new),
	PAWN(1, "P", "pawn", Pawn::new),
	QUEEN(9, "Q", "queen", Queen::new),
	ROOK(5, "R", "rook", Rook::new);

	public static final int HALF_DIVIDER = 2;

	private final double score;
	private final String initial;
	private final String name;
	private final BiFunction<Team, Coordinate, Piece> factory;

	PieceType(double score, String initial, String name, BiFunction<Team, Coordinate, Piece> factory) {
		this.score = score;
		this.initial = initial;
		this.name = name;
		this.factory = factory;
	}

	public static PieceType ofName(String name) {
		return Arrays.stream(values())
				.filter(pieceType -> pieceType.name.equals(name))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}

	public static BiFunction<Team, Coordinate, Piece> getFactoryOfName(String name) {
		return ofName(name).factory;
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
