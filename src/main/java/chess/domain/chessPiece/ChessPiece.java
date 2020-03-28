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
		if (pieceState.canMove(sourcePosition, targetPosition)) {
			pieceState = pieceState.shiftNextState(pieceColor);
			return true;
		}
		return false;
	}

	@Override
	public boolean canCatch(Position sourcePosition, Position targetPosition) {
		if (pieceState.canCatch(sourcePosition, targetPosition)) {
			pieceState = pieceState.shiftNextState(pieceColor);
			return true;
		}
		return false;
	}

	public boolean isSamePieceColorWith(ChessPiece chessPiece) {
		return this.pieceColor.equals(chessPiece.pieceColor);
	}

	public boolean isSame(PieceColor pieceColor) {
		return this.pieceColor.equals(pieceColor);
	}

	public abstract String getName();

	public abstract double getScore();

	@Override
	public String toString() {
		return getName();
	}
}
