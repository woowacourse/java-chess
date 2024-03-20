package domain;

import static domain.PieceMoveResult.*;
import static domain.PieceType.*;

import java.util.Optional;

class Bishop extends AbstractMoveStraightMovePiece {
	Bishop(Position position, Team team) {
		super(position, team);
	}

	@Override
	public Optional<PieceMoveResult> tryMoveAssumeAlone(Position targetPosition,
		PiecesOnChessBoard piecesOnChessBoard) {
		Position nowPosition = getPosition();
		int absRowDistance = Math.abs(nowPosition.rowDistance(targetPosition));
		int absColDistance = Math.abs(nowPosition.columnDistance(targetPosition));
		if (absColDistance != absRowDistance) {
			return Optional.of(FAILURE);
		}
		return Optional.empty();
	}

	@Override
	public PieceType getPieceType() {
		return BISHOP;
	}
}
