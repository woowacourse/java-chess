package domain;

import static domain.PieceMoveResult.*;

import java.util.Optional;

public abstract class AbstractCatchOnMovePiece extends AbstractPiece {
	public AbstractCatchOnMovePiece(Position position, Team team) {
		super(position, team);
	}

	@Override
	public final PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
		Optional<PieceMoveResult> pieceMoveResult = attemptMove(targetPosition, piecesOnChessBoard);
		if (pieceMoveResult.isPresent()) {
			return pieceMoveResult.get();
		}
		if (isMyTeam(piecesOnChessBoard, targetPosition)) {
			return FAILURE;
		}
		if (isOtherTeam(piecesOnChessBoard, targetPosition)) {
			return CATCH;
		}
		return SUCCESS;
	}

	protected abstract Optional<PieceMoveResult> attemptMove(Position targetPosition,
		PiecesOnChessBoard piecesOnChessBoard);

	private boolean isMyTeam(PiecesOnChessBoard piecesOnChessBoard, Position targetPosition) {
		Optional<Team> targetTeam = piecesOnChessBoard.whichTeam(targetPosition);
		return targetTeam.isPresent() && targetTeam.get().equals(getTeam());
	}

	private boolean isOtherTeam(PiecesOnChessBoard piecesOnChessBoard, Position targetPosition) {
		Optional<Team> targetTeam = piecesOnChessBoard.whichTeam(targetPosition);
		return targetTeam.isPresent() && targetTeam.get().equals(getTeam().otherTeam());
	}
}
