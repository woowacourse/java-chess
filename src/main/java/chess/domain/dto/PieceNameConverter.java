package chess.domain.dto;

import chess.domain.Side;
import chess.domain.piece.*;

import java.util.Arrays;
import java.util.function.Predicate;

public enum PieceNameConverter {
	KING("K", "k", piece -> piece instanceof King),
	QUEEN("Q", "q", piece -> piece instanceof Queen),
	ROOK("R", "r", piece -> piece instanceof Rook),
	BISHOP("B", "b", piece -> piece instanceof Bishop),
	KNIGHT("N", "n", piece -> piece instanceof Knight),
	PAWN("P", "p", piece -> piece instanceof Pawn);

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
