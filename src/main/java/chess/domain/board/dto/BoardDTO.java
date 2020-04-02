package chess.domain.board.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Board;

public class BoardDTO {
	private final Map<String, String> board;

	private BoardDTO(Map<String, String> board) {
		this.board = board;
	}

	public static BoardDTO of(Board board) {
		Map<String, String> boardDto = board.getBoard().entrySet()
			.stream()
			.collect(Collectors.toMap(
				entry -> entry.getKey().getName(),
				entry -> entry.getValue().getSymbol(),
				(e1, e2) -> e1, LinkedHashMap::new
			));

		return new BoardDTO(boardDto);
	}

	public Map<String, String> getBoard() {
		return board;
	}
}
