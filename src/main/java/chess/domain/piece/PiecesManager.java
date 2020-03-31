package chess.domain.piece;

import java.util.List;

import chess.domain.board.Position;

public class PiecesManager {
	private final Pieces whitePieces;
	private final Pieces blackPieces;
	private boolean turnFlag;

	public PiecesManager(final Pieces whitePieces, final Pieces blackPieces) {
		this.whitePieces = whitePieces;
		this.blackPieces = blackPieces;
		this.turnFlag = true;
	}

	public List<Position> movingTrace(Position source, Position target) {
		List<Position> trace;

		if (turnFlag) {
			trace = whitePieces.findTrace(source, target);
			return trace;
		}
		trace = blackPieces.findTrace(source, target);
		return trace;
	}

	public void move(Position source, Position target) {
		if (turnFlag) {
			whitePieces.moveFromTo(source, target);
			blackPieces.kill(target);
			turnFlag = false;
		} else {
			blackPieces.moveFromTo(source, target);
			whitePieces.kill(target);
			turnFlag = true;
		}
	}

	public boolean canMove(Position source, Position target) {
		boolean hasSourceAndTargetSeparately = (whitePieces.hasPiece(source) && blackPieces.hasPiece(target))
			|| (whitePieces.hasPiece(target) && blackPieces.hasPiece(source));
		boolean hasOnlySourceOrTarget =
			(whitePieces.hasPiece(source) && !blackPieces.hasPiece(target) && !whitePieces.hasPiece(target))
				|| (!whitePieces.hasPiece(target) && !blackPieces.hasPiece(target) && blackPieces.hasPiece(source));

		return hasSourceAndTargetSeparately || hasOnlySourceOrTarget;
	}

	public boolean isKingDie() {
		return whitePieces.isKingDie() || blackPieces.isKingDie();
	}

	public double whitePiecesStatus() {
		return whitePieces.calculateStatus();
	}

	public double blackPiecesStatus() {
		return blackPieces.calculateStatus();
	}
}
