package chess.domain.dto;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class PieceEditDto {
	private Position targetPosition;
	private Piece wantPiece;

	public PieceEditDto(Position targetPosition, Piece wantPiece) {
		this.targetPosition = targetPosition;
		this.wantPiece = wantPiece;
	}

	public String getTargetPositionValue() {
		return targetPosition.getValue();
	}

	public String getWantPieceName() {
		return wantPiece.getName();
	}
}
