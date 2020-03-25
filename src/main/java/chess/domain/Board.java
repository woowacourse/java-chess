package chess.domain;

import java.util.List;

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
		if (destinationPiece != null) {
			duplicatePositionHandler(piece, destinationPiece);
		}
		validateNoObstacle(source, destination);
		source.calculateDirection(destination);
		piece.move(destination);
	}

	private void validateNoObstacle(Position source, Position destination) {
		List<Position> positionsInBetween = source.getPositionsInBetween(destination);
		for (Position position : positionsInBetween) {
			if (pieces.findByPosition(position) != null) {
				throw new IllegalArgumentException("경로에 다른 말이 있어 움직일 수 없습니다.");
			}
		}
	}

	private void duplicatePositionHandler(Piece piece, Piece destinationPiece) {
		if (piece.isSameTeam(destinationPiece)) {
			throw new IllegalArgumentException("해당 자리에 같은 팀 말이 있기 때문에 말을 움직일 수 없습니다!");
		}
		destinationPiece.kill();
	}
}
