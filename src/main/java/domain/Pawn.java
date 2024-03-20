package domain;

import static domain.PieceMoveResult.*;
import static domain.PieceType.*;

import java.util.List;
import java.util.Optional;

class Pawn extends AbstractPiece {

	private final Position initialPosition;

	Pawn(Position position, Team team) {
		super(position, team);
		this.initialPosition = position;
	}

	@Override
	public PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
		if (isMoveForward(targetPosition) && isEmpty(targetPosition, piecesOnChessBoard)) {
			return SUCCESS;
		}
		if (isMoveForwardTwo(targetPosition, piecesOnChessBoard) && isEmpty(targetPosition, piecesOnChessBoard)) {
			return SUCCESS;
		}
		if (isMoveDiagonal(targetPosition) && isOtherTeam(targetPosition, piecesOnChessBoard)) {
			return CATCH;
		}
		return FAILURE;
	}

	private boolean isMoveForward(Position targetPosition) {
		Position nowPosition = getPosition();
		boolean sameColumn = nowPosition.isSameColumn(targetPosition);
		boolean rightDirection = nowPosition.rowDistance(targetPosition) == forwardDirection();
		return rightDirection && sameColumn;
	}

	private int forwardDirection() {
		Team team = getTeam();
		if (team.equals(Team.WHITE)) {
			return 1;
		}
		return -1;
	}

	private boolean isEmpty(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
		Optional<Team> targetTeam = piecesOnChessBoard.whichTeam(targetPosition);
		return targetTeam.isEmpty();
	}

	private boolean isMoveForwardTwo(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
		Position nowPosition = getPosition();
		List<Position> route = nowPosition.route(targetPosition);
		boolean rightDirection = nowPosition.rowDistance(targetPosition) == 2 * forwardDirection();
		boolean sameColumn = nowPosition.isSameColumn(targetPosition);
		boolean firstMove = nowPosition.equals(initialPosition);
		boolean allPieceOnRouteIsEmpty = isAllPieceOnRouteIsEmpty(piecesOnChessBoard, route);
		return rightDirection && sameColumn && firstMove && allPieceOnRouteIsEmpty;
	}

	private boolean isAllPieceOnRouteIsEmpty(PiecesOnChessBoard piecesOnChessBoard, List<Position> route) {
		return route.stream()
			.map(piecesOnChessBoard::whichTeam)
			.allMatch(Optional::isEmpty);
	}

	private boolean isMoveDiagonal(Position targetPosition) {
		Position nowPosition = getPosition();
		int rowDistance = nowPosition.rowDistance(targetPosition);
		int columnDistance = nowPosition.columnDistance(targetPosition);
		return rowDistance == forwardDirection() && Math.abs(columnDistance) == 1;
	}

	private boolean isOtherTeam(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
		Optional<Team> targetTeam = piecesOnChessBoard.whichTeam(targetPosition);
		return targetTeam.isPresent() && !targetTeam.get().equals(getTeam());
	}

	@Override
	public PieceType getPieceType() {
		return PAWN;
	}
}
