package chess.domain.web;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;

public class PieceDto {
	private final PieceType pieceType;
	private final Color color;

	public PieceDto(Piece piece) {
		this.pieceType = piece.getPieceType();
		this.color = piece.getColor();
	}
}