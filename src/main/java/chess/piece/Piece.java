package chess.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Piece {

	protected final PieceNameType name;
	protected final boolean team;

	Piece(PieceNameType name, boolean team) {
		this.name = name;
		this.team = team;
	}

	public static Piece of(PieceNameType pieceNameType, boolean team) {
		return PieceCache.pieces.stream()
			.filter(piece -> piece.name == pieceNameType)
			.filter(piece -> piece.team == team)
			.findFirst()
			.orElseThrow(() -> new AssertionError("카드를 찾을 수 없습니다."));
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Piece piece = (Piece)o;
		return team == piece.team &&
			name == piece.name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, team);
	}

	private static class PieceCache {
		private static final List<Piece> pieces = new ArrayList<>();

		static {
			for (PieceNameType pieceNameType : PieceNameType.values()) {
				pieces.add(new Piece(pieceNameType, false));
				pieces.add(new Piece(pieceNameType, true));
			}
		}
	}
}
