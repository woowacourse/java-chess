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
		if (!containsKey(key)) {
			throw new IllegalArgumentException("기물이 존재하지 않습니다.");
		}
		return board.get(key);
	}

	public boolean containsKey(String key) {
		return board.containsKey(key);
	}

	public void update(String from, String to) {
		if (containsKey(to)) {
			throw new IllegalArgumentException("아군 기물이 위치하고 있습니다.");
		}
		board.put(to, board.get(from));
		board.remove(from);
	}
}
