package chess.web;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import chess.controller.converter.File;
import chess.controller.converter.Rank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class WebBoardDto {

	private static final String EMPTY = "EMPTY";

	private final Map<String, Object> value;

	public WebBoardDto(Map<Position, Piece> board) {
		value = new HashMap<>();
		for (File file : File.values()) {
			initialize(board, file);
		}
	}

	private void initialize(Map<Position, Piece> board, File file) {
		for (Rank rank : Rank.values()) {
			String key = file.getName() + rank.getName();

			Optional<Piece> nullablePiece = Optional.ofNullable(
				board.get(new Position(rank.getRow(), file.getColumn())));

			nullablePiece.ifPresentOrElse(
				piece -> value.put(key, piece),
				() -> value.put(key, EMPTY));
		}
	}

	public Map<String, Object> getValue() {
		return new HashMap<>(value);
	}
}
