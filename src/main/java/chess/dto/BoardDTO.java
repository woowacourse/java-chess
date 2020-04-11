package chess.dto;

import static java.util.stream.Collectors.*;

import java.util.Map;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class BoardDTO {
	private final Map<Position, Piece> board;

	public BoardDTO(Map<Position, Piece> board) {
		this.board = board;
	}

	public static BoardDTO of(Board board) {
		return new BoardDTO(board.getBoard());
	}

	public String getPieceIn(String key) {
		return board.get(Position.of(key)).getName();
	}

	public Map<String, String> getBoard() {
		return board.entrySet()
			.stream()
			.collect(toMap(
				entry -> entry.getKey().getName(),
				entry -> entry.getValue().getSymbol()
				));
	}
}
