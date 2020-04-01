package chess.util;

import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Board;

public class WebOutputRenderer {
	public static Map<String, Object> toModel(Board board) {
		return board.getPieces()
			.entrySet()
			.stream()
			.collect(Collectors.toMap(x -> x.getKey().toString(), x -> x.getValue().getName()));
	}
}
