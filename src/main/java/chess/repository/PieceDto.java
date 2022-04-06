package chess.repository;

import java.util.Objects;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class PieceDto {

	private final Piece piece;
	private final Position position;

	public PieceDto(Piece piece, Position position) {
		this.piece = piece;
		this.position = position;
	}

	public Piece getPiece() {
		return piece;
	}

	public Position getPosition() {
		return position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		PieceDto pieceDto = (PieceDto)o;
		return Objects.equals(getPiece(), pieceDto.getPiece()) && Objects.equals(getPosition(),
			pieceDto.getPosition());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getPiece(), getPosition());
	}
}
