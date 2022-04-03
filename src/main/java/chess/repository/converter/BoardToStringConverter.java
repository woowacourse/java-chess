package chess.repository.converter;

import static java.util.stream.Collectors.*;

import java.util.Map;

import chess.converter.File;
import chess.converter.Rank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class BoardToStringConverter {

	public static Map<String, String> from(Map<Position, Piece> board) {
		return board.entrySet().stream()
			.collect(toMap(
				entry -> convertPosition(entry.getKey()),
				entry -> entry.getValue().toString())
			);
	}

	private static String convertPosition(Position position) {
		Rank rank = Rank.from(position.getRow());
		File file = File.from(position.getColumn());
		return file.getName() + rank.getName();
	}
}
