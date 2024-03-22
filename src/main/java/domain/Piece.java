package domain;

import static domain.PieceMoveResult.FAILURE;

public abstract class Piece {
	private final Team team;
	private Position position;

	Piece(Position position, Team team) {
		this.position = position;
		this.team = team;
	}

	final PieceMoveResult move(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard) {
		if (targetPosition == position) {
			return FAILURE;
		}
		PieceMoveResult pieceMoveResult = tryMove(targetPosition, piecesOnChessBoard);
		if (!pieceMoveResult.equals(FAILURE)) {
			position = targetPosition;
		}
		return pieceMoveResult;
	}

	abstract PieceMoveResult tryMove(Position targetPosition, PiecesOnChessBoard piecesOnChessBoard);

	public boolean isOn(Position position) {
		return this.position.equals(position);
	}

	public Team getTeam() {
		return team;
	}
	public abstract PieceType getPieceType();

	public int getColumn() {
		return position.getColumn();
	}

	public int getRow() {
		return position.getRow();
	}

	protected Position getPosition() {
		return position;
	}
}
