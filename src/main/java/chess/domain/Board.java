package chess.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Board {
	private final Map<String, Piece> board;
	private final Map<String, Piece> reversedBoard;

	public Board(Map<String, Piece> board) {
		this.board = Map.copyOf(board);
		reversedBoard = Map.copyOf(board);
	}

	private Map<String, String> getReversedBoard() {
		return reversedBoard.entrySet()
			.stream()
			.collect(Collectors.toMap(entry -> Position.of(entry.getKey()).reverse().getName(),
				entry -> entry.getValue().getUpperName(),
				(e1, e2) -> e1, LinkedHashMap::new));
	}

	public Map<String, String> toMap() {
		Map<String, String> result = board.entrySet()
			.stream()
			.collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getName(),
				(e1, e2) -> e1, LinkedHashMap::new));
		result.putAll(getReversedBoard());
		return result;
	}
}
