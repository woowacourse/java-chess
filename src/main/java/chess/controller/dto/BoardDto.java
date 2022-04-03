package chess.controller.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import chess.converter.File;
import chess.controller.converter.PieceToStringConverter;
import chess.converter.Rank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class BoardDto {

	private static final String EMPTY = "ꕤ";

	private final Map<String, String> value;

	public BoardDto(Map<Position, Piece> board) {
		value = new HashMap<>();
		for (File file : File.values()) {
			initialize(board, file);
		}
	}

	private void initialize(Map<Position, Piece> board, File file) {
		for (Rank rank : Rank.values()) {
			String key = file.getName() + rank.getName();

			Optional<Piece> piece = Optional.ofNullable(
				board.get(new Position(rank.getRow(), file.getColumn())));

			piece.ifPresentOrElse(
				symbol -> value.put(key, PieceToStringConverter.from(symbol)),
				() -> value.put(key, EMPTY));
		}
	}

	public Map<String, String> getValue() {
		return new HashMap<>(value);
	}
}
