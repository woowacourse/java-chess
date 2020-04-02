package chess.domain.dto;

import chess.domain.Side;
import chess.domain.piece.*;

import java.util.Arrays;
import java.util.function.Predicate;

public enum PieceNameConverter {
	KING("\u265A", "\u2654", piece -> piece instanceof King),
	QUEEN("\u265B", "\u2655", piece -> piece instanceof Queen),
	ROOK("\u265C", "\u2656", piece -> piece instanceof Rook),
	BISHOP("\u265D", "\u2657", piece -> piece instanceof Bishop),
	KNIGHT("\u265E", "\u2658", piece -> piece instanceof Knight),
	PAWN("\u265F", "\u2659", piece -> piece instanceof Pawn);

	private String blackName;
	private String whiteName;
	private Predicate<Piece> checker;

	PieceNameConverter(String blackName, String whiteName, Predicate<Piece> checker) {
		this.blackName = blackName;
		this.whiteName = whiteName;
		this.checker = checker;
	}

	public static String run(Piece piece) {
		return Arrays.stream(values())
				.filter(p -> p.check(piece))
				.map(p -> p.getName(piece))
				.findAny()
				.orElseThrow(AssertionError::new);
	}

	public String getName(Piece piece) {
		if (piece.isSameSide(Side.BLACK)) {
			return blackName;
		}
		return whiteName;
	}

	private boolean check(Piece piece) {
		return checker.test(piece);
	}
}
