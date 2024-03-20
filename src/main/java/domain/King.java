package domain;

import static domain.PieceMoveResult.*;

import java.util.Optional;

public class King extends AbstractPiece {
	public King(Position position, Team team) {
		super(position, team);
	}

	@Override
	public PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
		Position nowPosition = getPosition();
		int absRowDistance = Math.abs(nowPosition.rowDistance(targetPosition));
		int absColDistance = Math.abs(nowPosition.columnDistance(targetPosition));
		if (absRowDistance > 1 || absColDistance > 1) {
			return FAILURE;
		}
		if (isMyTeam(piecesOnChessBoard, targetPosition)) {
			return FAILURE;
		}
		if (isOtherTeam(piecesOnChessBoard, targetPosition)) {
			return CATCH;
		}
		return SUCCESS;
	}

	private boolean isMyTeam(PiecesOnChessBoard piecesOnChessBoard, Position targetPosition) {
		Optional<Team> targetTeam = piecesOnChessBoard.whichTeam(targetPosition);
		return targetTeam.isPresent() && targetTeam.get().equals(getTeam());
	}

	private boolean isOtherTeam(PiecesOnChessBoard piecesOnChessBoard, Position targetPosition) {
		Optional<Team> targetTeam = piecesOnChessBoard.whichTeam(targetPosition);
		return targetTeam.isPresent() && targetTeam.get().equals(getTeam().otherTeam());
	}
}
