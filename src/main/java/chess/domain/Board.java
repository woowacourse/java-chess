package chess.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Board {
	private final Map<String, Piece> board;

	public Board(Map<String, Piece> board) {
		this.board = board;
	}

	public Map<String, Piece> getReversed() {
		return board.entrySet()
			.stream()
			.collect(Collectors.toMap(
				entry -> Position.getReversedNameOf(entry.getKey()),
				Map.Entry::getValue,
				(e1, e2) -> e1,
				LinkedHashMap::new));
	}

	public Piece get(String key) {
		return board.get(key);
	}

	public boolean containsKey(String key) {
		return board.containsKey(key);
	}
}
