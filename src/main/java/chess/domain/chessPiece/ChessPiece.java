package chess.domain.chessPiece;

import java.util.Objects;

import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

public abstract class ChessPiece implements Movable, Catchable {

	protected final PieceColor pieceColor;
	protected PieceState pieceState;

	public ChessPiece(PieceColor pieceColor, PieceState pieceState) {
		Objects.requireNonNull(pieceColor, "피스 색상이 null입니다.");
		Objects.requireNonNull(pieceState, "피스 전략이 null입니다.");
		this.pieceColor = pieceColor;
		this.pieceState = pieceState;
	}

	@Override
	public boolean canLeap() {
		return pieceState.canLeap();
	}

	@Override
	public boolean canMove(Position sourcePosition, Position targetPosition) {
		validate(sourcePosition, targetPosition);

		if (pieceState.canMove(sourcePosition, targetPosition)) {
			pieceState = pieceState.shiftNextState(pieceColor);
			return true;
		}
		return false;
	}

	@Override
	public boolean canCatch(Position sourcePosition, Position targetPosition) {
		validate(sourcePosition, targetPosition);

		if (pieceState.canCatch(sourcePosition, targetPosition)) {
			pieceState = pieceState.shiftNextState(pieceColor);
			return true;
		}
		return false;
	}

	private void validate(Position sourcePosition, Position targetPosition) {
		Objects.requireNonNull(sourcePosition, "소스 위치가 null입니다.");
		Objects.requireNonNull(targetPosition, "타겟 위치가 null입니다.");
	}

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

	// TODO: 2020/03/30 사용 위치 확인하고 지우기.
	@Override
	public String toString() {
		return getName();
	}

}
