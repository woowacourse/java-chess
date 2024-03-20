package domain;

import static domain.PieceMoveResult.*;

import java.util.Optional;

public class King extends AbstractCatchOnMovePiece {
	public King(Position position, Team team) {
		super(position, team);
	}

	@Override
	public Optional<PieceMoveResult> attemptMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
		Position nowPosition = getPosition();
		int absRowDistance = Math.abs(nowPosition.rowDistance(targetPosition));
		int absColDistance = Math.abs(nowPosition.columnDistance(targetPosition));
		if (absRowDistance > 1 || absColDistance > 1) {
			return Optional.of(FAILURE);
		}
		return Optional.empty();
	}
}
