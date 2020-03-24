package chess.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Rank {
	private List<Optional<Piece>> pieces = new ArrayList<>();

	public Rank() {
		for (int i = 0; i < 8; i++) {
			pieces.add(Optional.empty());
		}
	}

	public List<Optional<Piece>> getPieces() {
		return pieces;
	}
}
