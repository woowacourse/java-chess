package chess.domain.board;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class Rank {
	private static final int SIZE = 8;

	private List<Piece> pieces;

	public Rank(List<Piece> pieces) {
		validate(pieces);
		this.pieces = new ArrayList<>(pieces);
	}

	private void validate(List<Piece> pieces) {
		if (pieces.size() != SIZE) {
			throw new IllegalArgumentException("체스판 한 줄은 8개 이어야 합니다.");
		}
	}

	public static Rank createBlanks(int y) {
		List<Piece> pieces = new ArrayList<>();
		for (int x = 0; x < SIZE; x++) {
			pieces.add(new Blank(Position.of(x, y)));
		}
		return new Rank(pieces);
	}

	public List<Piece> getPieces() {
		return Collections.unmodifiableList(pieces);
	}
}
