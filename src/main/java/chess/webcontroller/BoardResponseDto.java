package chess.webcontroller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import chess.converter.File;
import chess.converter.Rank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class BoardResponseDto {

	private static final String EMPTY = "EMPTY";

	private final Map<String, Object> value;

	public BoardResponseDto(Map<Position, Piece> board) {
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
