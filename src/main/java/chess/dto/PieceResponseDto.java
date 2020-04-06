package chess.dto;

import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Piece;
import chess.util.PieceNameConverter;

public class PieceResponseDto {
	private String position;
	private String pieceType;

	public PieceResponseDto(Coordinates coordinates, Piece piece) {
		this.position = coordinates.getName();
		this.pieceType = PieceNameConverter.toPieceType(piece);
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPieceType() {
		return pieceType;
	}

	public void setPieceType(String pieceType) {
		this.pieceType = pieceType;
	}
}
