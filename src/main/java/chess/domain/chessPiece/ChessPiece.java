package chess.domain.chessPiece;

import java.util.Objects;

import chess.domain.chessPiece.pieceState.State;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

public abstract class ChessPiece implements Movable, Catchable {

	protected final PieceColor pieceColor;
	protected State state;

	public ChessPiece(PieceColor pieceColor, State state) {
		Objects.requireNonNull(pieceColor, "피스 색상이 null입니다.");
		Objects.requireNonNull(state, "피스 전략이 null입니다.");
		this.pieceColor = pieceColor;
		this.state = state;
	}

	@Override
	public boolean canLeap() {
		return state.canLeap();
	}

	@Override
	public boolean canMove(Position sourcePosition, Position targetPosition) {
		if (state.canMove(sourcePosition, targetPosition)) {
			state = state.shiftNextState(pieceColor);
			return true;
		}
		return false;
	}

	@Override
	public boolean canCatch(Position sourcePosition, Position targetPosition) {
		if (state.canCatch(sourcePosition, targetPosition)) {
			state = state.shiftNextState(pieceColor);
			return true;
		}
		return false;
	}

	public boolean isSamePieceColorWith(ChessPiece chessPiece) {
		return this.pieceColor.equals(chessPiece.pieceColor);
	}

	public boolean isSamePieceColorWith(PieceColor pieceColor) {
		return this.pieceColor.equals(pieceColor);
	}

	public abstract String getName();

	public abstract double getScore();

	@Override
	public String toString() {
		return getName();
	}
}
