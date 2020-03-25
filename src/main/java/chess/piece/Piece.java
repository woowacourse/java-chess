package chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Piece {

	protected final PieceType type;
	protected final boolean isBlack;

	Piece(PieceType type, boolean isBlack) {
		this.type = type;
		this.isBlack = isBlack;
	}

	public static Piece of(PieceType pieceType, boolean isBlack) {
		return PieceCache.pieces.stream()
			.filter(piece -> piece.is(pieceType, isBlack))
			.findFirst()
			.orElseThrow(() -> new AssertionError("카드를 찾을 수 없습니다."));
	}

	public boolean is(PieceType pieceType, boolean black) {
		return is(pieceType) && is(black);
	}

	public boolean is(boolean isBlack) {
		return this.isBlack == isBlack;
	}

	public boolean is(PieceType pieceType) {
		return this.type == pieceType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Piece piece = (Piece)o;
		return isBlack == piece.isBlack &&
			type == piece.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, isBlack);
	}

	@Override
	public String toString() {
		// TODO
		if (isBlack) {
			return type.getValue().toUpperCase();
		}
		return type.getValue();
	}

	private static class PieceCache {
		private static final List<Piece> pieces = new ArrayList<>();

		static {
			for (PieceType pieceType : PieceType.values()) {
				pieces.add(new Piece(pieceType, false));
				pieces.add(new Piece(pieceType, true));
			}
		}
	}
}
