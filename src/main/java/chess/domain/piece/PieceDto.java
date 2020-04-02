package chess.domain.piece;

import java.util.Objects;

import chess.domain.board.PieceFactory;
import chess.domain.position.Position;

public class PieceDto {
	private Position position;
	private Piece piece;

	private PieceDto(Position position, Piece pieceName) {
		this.position = position;
		this.piece = pieceName;
	}

	public static PieceDto of(String position, String pieceName) {
		return new PieceDto(Position.of(position), PieceFactory.of(pieceName));
	}

	public static PieceDto of(Position position, Piece piece) {
		return new PieceDto(position, piece);
	}

	public Position getPosition() {
		return position;
	}

	public Piece getPiece() {
		return piece;
	}

	public String getPositionValue() {
		return position.getValue();
	}

	public String getPieceName() {
		return piece.getName();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		PieceDto pieceDto = (PieceDto)o;
		return Objects.equals(position, pieceDto.position) &&
			Objects.equals(piece, pieceDto.piece);
	}

	@Override
	public int hashCode() {
		return Objects.hash(position, piece);
	}
}
