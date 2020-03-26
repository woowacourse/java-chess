package chess.domain.board;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.Turn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Boards {
	private static final int LOWER_BOARD = 0;
	private static final int UPPER_BOARD = 1;
	private static final String KING = "k";

	private final List<Board> boards;

	public Boards(List<Board> boards) {
		this.boards = boards;
	}

	public static Boards of(Map<String, Piece> lowerBoard, Map<String, Piece> upperBoard) {
		List<Board> boards = new ArrayList<>();

		boards.add(new Board(lowerBoard));
		boards.add(new Board(upperBoard));

		return new Boards(boards);
	}

	public Map<String, String> getTotal() {
		Map<String, String> result = new LinkedHashMap<>(getLowerBoard());
		result.putAll(getUpperBoard());
		return result;
	}

	private Map<String, String> getLowerBoard() {
		return boards.get(LOWER_BOARD).getBoard().entrySet()
			.stream()
			.collect(Collectors.toMap(
				Map.Entry::getKey,
				entry -> entry.getValue().getName(),
				(e1, e2) -> e1, LinkedHashMap::new));
	}

	private Map<String, String> getUpperBoard() {
		return boards.get(UPPER_BOARD).getReversedBoard().entrySet()
			.stream()
			.collect(Collectors.toMap(
				Map.Entry::getKey,
				entry -> entry.getValue().getUpperName(),
				(e1, e2) -> e1, LinkedHashMap::new));
	}

	public boolean hasPieceIn(List<String> path) {
		return path.stream()
			.anyMatch(key -> getTotal().containsKey(key));
	}

	public void move(String from, String to, Turn turn) {
		boards.get(turn.self()).update(from, to);
		boards.get(turn.enemy()).remove(Position.getReversedNameOf(to));
	}

	public boolean isBothKingAlive() {
		return getTotal().values()
			.stream()
			.filter(piece -> piece.toLowerCase().equals(KING))
			.count() == 2;
	}
}
