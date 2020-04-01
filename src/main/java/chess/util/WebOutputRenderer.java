package chess.util;

import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Board;
import chess.domain.piece.Color;

public class WebOutputRenderer {
	public static Map<String, Object> toModel(Board board) {
		return board.getPieces()
			.entrySet()
			.stream()
			.collect(Collectors.toMap(x -> x.getKey().toString(), x -> x.getValue().getName()));
	}

	public static Map<String, Object> scoreToModel(Map<Color, Double> scores) {
		return scores.entrySet()
			.stream()
			.collect(Collectors.toMap(x -> x.getKey().name(), x -> String.valueOf(x.getValue())));
	}
}
