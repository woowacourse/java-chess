package domain;

import static domain.PieceMoveResult.*;

import java.util.Optional;

class Rook extends AbstractMoveStraightMovePiece {
	Rook(Position position, Team team) {
		super(position, team);
	}

	@Override
	public Optional<PieceMoveResult> tryMoveAssumeAlone(Position targetPosition,
		PiecesOnChessBoard piecesOnChessBoard) {
		Position nowPosition = getPosition();
		if (!nowPosition.isSameColumn(targetPosition) && !nowPosition.isSameRow(targetPosition)) {
			return Optional.of(FAILURE);
		}
		return Optional.empty();
	}
}
