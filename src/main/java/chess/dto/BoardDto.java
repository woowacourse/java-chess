package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.pieces.Pieces;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class BoardDto {
	private final Map<Position, PieceDto> board = new HashMap<>();

	public BoardDto(Pieces pieces) {
		for (Piece piece : pieces.getPieces()) {
			board.put(piece.getPosition(), new PieceDto(piece));
		}
	}

	public Map<Position, PieceDto> getBoardDto() {
		return board;
	}}