package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;

public class Board {
	private final Pieces pieces;

	public Board() {
		this.pieces = PieceFactory.getPieces();
	}

	public Pieces getPieces() {
		return pieces;
	}

	public void movePiece(Position source, Position destination) {
		Piece piece = pieces.findByPosition(source);
		Piece destinationPiece = pieces.findByPosition(destination);
		if (destinationPiece != null && piece.isSameTeam(destinationPiece)) {
			throw new IllegalArgumentException("말을 움직일 수 없습니다!");
		}
		piece.move(destination);
	}
}
