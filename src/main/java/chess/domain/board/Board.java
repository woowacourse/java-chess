package chess.domain.board;

import static java.util.stream.Collectors.*;

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

	public Map<String, Piece> getBoard() {
		return Map.copyOf(board);
	}

	public Map<String, Piece> getReversedBoard() {
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
		Piece piece = get(from);
		if (containsKey(to)) {
			throw new IllegalArgumentException("아군 기물이 위치하고 있습니다.");
		}

		piece.moveTo(Position.of(to));
		board.put(to, piece);

		remove(from);
	}

	public double getScore() {
		return board.entrySet()
			.stream()
			.collect(groupingBy(
					entry -> entry.getKey().substring(0, 1),
					mapping(entry -> entry.getValue().getName(), toList())))
			.values().stream()
			.mapToDouble(Score::calculateScoreOf)
			.sum();
	}

	public void remove(String key) {
		board.remove(key);
	}
}
