package chess.dto;

import static java.util.stream.Collectors.*;

import java.util.Map;

import chess.domain.board.Board;

public class BoardDTO {
	private final Map<String, String> board;

	public BoardDTO(Map<String, String> board) {
		this.board = board;
	}

	public static BoardDTO of(Board board) {
		Map<String, String> map = board.getBoard()
			.entrySet()
			.stream()
			.collect(toMap(
				entry -> entry.getKey().getName(),
				entry -> entry.getValue().getName()
			));

		return new BoardDTO(map);
	}

	public String getPieceIn(String key) {
		return board.get(key);
	}

	public Map<String, String> getBoard() {
		return board;
	}
}
