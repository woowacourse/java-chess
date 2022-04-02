package chess.dto;

import chess.controller.PieceSymbol;
import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class BoardDto {

	private final Map<Position, String> board;

	private BoardDto(final Map<Position, String> board) {
		this.board = board;
	}

	public static BoardDto of(Board board) {
		Map<Position, String> symbolBoard = board.getBoard().entrySet().stream()
				.collect(Collectors.toMap(Entry::getKey, e -> PieceSymbol.of(e.getValue())));
		return new BoardDto(symbolBoard);
	}

	public Map<Position, String> getBoard() {
		return new HashMap<>(board);
	}
}
