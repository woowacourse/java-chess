package domain.board;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import domain.piece.King;
import domain.piece.Piece;
import domain.piece.position.Position;

public class Rank {
	private List<Piece> pieces;

	public Rank(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public Optional<Piece> findPiece(Position position) {
		return pieces.stream()
			.filter(piece -> piece.getPosition().isSamePosition(position))
			.findFirst();
	}

	public int calculateCountOfKing() {
		return (int)pieces.stream()
			.filter(value -> value instanceof King)
			.count();
	}

	public void remove(Piece piece) {
		pieces.remove(piece);
	}

	public void add(Piece piece) {
		pieces.add(piece);
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Rank rank = (Rank)o;
		return Objects.equals(pieces, rank.pieces);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pieces);
	}
}
