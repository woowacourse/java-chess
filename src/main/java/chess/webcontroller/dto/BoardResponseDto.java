package chess.webcontroller.dto;

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

	private BoardResponseDto() {
		value = new HashMap<>();
		for (File file : File.values()) {
			initializeEmpty(value, file);
		}
	}

	private void initializeEmpty(Map<String, Object> board, File file) {
		for (Rank rank : Rank.values()) {
			String key = file.getName() + rank.getName();
			board.put(key, EMPTY);
		}
	}

	private BoardResponseDto(Map<Position, Piece> board) {
		value = new HashMap<>();
		for (File file : File.values()) {
			initializePiece(board, file);
		}
	}

	private void initializePiece(Map<Position, Piece> board, File file) {

		for (Rank rank : Rank.values()) {
			String key = file.getName() + rank.getName();

			Optional<Piece> nullablePiece = Optional.ofNullable(
				board.get(new Position(rank.getRow(), file.getColumn())));

			nullablePiece.ifPresentOrElse(
				piece -> value.put(key, piece),
				() -> value.put(key, EMPTY));
		}
	}

	public static BoardResponseDto from(Map<Position, Piece> board) {
		return new BoardResponseDto(board);
	}
	
	public static BoardResponseDto empty() {
		return new BoardResponseDto();
	}

	public Map<String, Object> getValue() {
		return new HashMap<>(value);
	}
}
