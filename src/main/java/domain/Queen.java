package domain;

import static domain.PieceMoveResult.*;

import java.util.Optional;

class Queen extends AbstractMoveStraightMovePiece {
	Queen(Position position, Team team) {
		super(position, team);
	}

	@Override
	public Optional<PieceMoveResult> tryMoveAssumeAlone(Position targetPosition,
		PiecesOnChessBoard piecesOnChessBoard) {
		Position nowPosition = getPosition();
		int absRowDistance = Math.abs(nowPosition.rowDistance(targetPosition));
		int absColDistance = Math.abs(nowPosition.columnDistance(targetPosition));
		if (absColDistance != absRowDistance && !nowPosition.isSameColumn(targetPosition) && !nowPosition.isSameRow(
			targetPosition)) {
			return Optional.of(FAILURE);
		}
		return Optional.empty();
	}
}
