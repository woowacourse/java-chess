package domain.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import domain.piece.Piece;
import domain.piece.position.Column;

public class Rank {
	private List<Piece> pieces;

	public Rank(List<Piece> pieces) {
		this.pieces = pieces;
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

	public List<String> getRankPieces() {
		List<String> rankPieces = new ArrayList<>();
		for (Column column : Column.values()) {
			if (pieces.stream().anyMatch(piece -> piece.isSameColumn(column))) {
				pieces.stream()
					.filter(piece -> piece.isSameColumn(column))
					.findFirst()
					.ifPresent(piece -> rankPieces.add(piece.showSymbol()));
				continue;
			}
			rankPieces.add("");
		}
		return rankPieces;
	}
}
