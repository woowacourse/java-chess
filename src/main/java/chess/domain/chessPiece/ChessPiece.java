package chess.domain.chessPiece;

import java.util.Objects;

import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

public abstract class ChessPiece implements Movable, Catchable {

	protected final PieceColor pieceColor;
	protected PieceState pieceState;

	protected ChessPiece(PieceColor pieceColor, PieceState pieceState) {
		Objects.requireNonNull(pieceColor, "피스 색상이 null입니다.");
		Objects.requireNonNull(pieceState, "피스 전략이 null입니다.");
		this.pieceColor = pieceColor;
		this.pieceState = pieceState;
	}

	@Override
	public abstract boolean canLeap();

	@Override
	public boolean canMove(Position sourcePosition, Position targetPosition) {
		validate(sourcePosition, targetPosition);

		if (isMovable(sourcePosition, targetPosition)) {
			pieceState = pieceState.shiftNextState();
			return true;
		}
		return false;
	}

	private void validate(final Position sourcePosition, final Position targetPosition) {
		Objects.requireNonNull(sourcePosition, "소스 위치가 null입니다.");
		Objects.requireNonNull(targetPosition, "타겟 위치가 null입니다.");
	}

	protected abstract boolean isMovable(Position sourcePosition, Position targetPosition);

	@Override
	public boolean canCatch(Position sourcePosition, Position targetPosition) {
		validate(sourcePosition, targetPosition);

		if (isCatchable(sourcePosition, targetPosition)) {
			pieceState = pieceState.shiftNextState();
			return true;
		}
		return false;
	}

	protected abstract boolean isCatchable(Position sourcePosition, Position targetPosition);

	public boolean isSamePieceColorWith(ChessPiece chessPiece) {
		Objects.requireNonNull(chessPiece, "체스 피스가 null입니다.");
		return this.pieceColor.equals(chessPiece.pieceColor);
	}

	public boolean isSame(PieceColor pieceColor) {
		Objects.requireNonNull(pieceColor, "체스 색상이 null입니다.");
		return this.pieceColor.equals(pieceColor);
	}

	public abstract String getName();

	public abstract double getScore();

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		final ChessPiece that = (ChessPiece)object;
		return this.getName().equals(that.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(pieceColor, pieceState);
	}

}
