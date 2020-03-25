package chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Piece {

	protected final PieceType type;
	protected final boolean team;

	Piece(PieceType type, boolean team) {
		this.type = type;
		this.team = team;
	}

	public static Piece of(PieceType pieceType, boolean team) {
		return PieceCache.pieces.stream()
			.filter(piece -> piece.type == pieceType)
			.filter(piece -> piece.team == team)
			.findFirst()
			.orElseThrow(() -> new AssertionError("카드를 찾을 수 없습니다."));
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
		return team == piece.team &&
			type == piece.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, team);
	}

	public boolean is(boolean isBlack) {
		return team == isBlack;
	}

	@Override
	public String toString() {
		return "Piece{" +
			"type=" + type +
			", team=" + team +
			'}';
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
