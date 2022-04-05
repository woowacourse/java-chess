package chess.converter;

import static java.util.stream.Collectors.*;

import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class BoardToStringConverter {

	public static Map<String, String> from(Map<Position, Piece> board) {
		return board.entrySet().stream()
			.collect(toMap(
				entry -> entry.getKey().toString(),
				entry -> entry.getValue().toString())
			);
	}
}
