package domain;

import static domain.PieceMoveResult.*;

import java.util.Optional;

public class Knight extends AbstractPiece {

	public Knight(Position position, Team team) {
		super(position, team);
	}

	@Override
	public PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
		if (!isMovablePosition(targetPosition)) {
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

	private boolean isMovablePosition(Position targetPosition) {
		Position nowPosition = getPosition();
		int absRowDistance = Math.abs(nowPosition.rowDistance(targetPosition));
		int absColDistance = Math.abs(nowPosition.columnDistance(targetPosition));
		return (absRowDistance == 2 && absColDistance == 1) || (absRowDistance == 1 && absColDistance == 2);
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
