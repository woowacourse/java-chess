package chess.domain.chessPiece;

import java.util.Objects;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.chessPiece.pieceType.PieceType;
import chess.domain.position.Position;

public class ChessPiece implements Movable, Catchable {

	protected final PieceType pieceType;
	protected PieceState pieceState;

	protected ChessPiece(final PieceType pieceType, final PieceState pieceState) {
		Objects.requireNonNull(pieceType, "피스 타입이 null입니다.");
		Objects.requireNonNull(pieceState, "피스 상태가 null입니다.");
		this.pieceType = pieceType;
		this.pieceState = pieceState;
	}

	public ChessPiece(final PieceType pieceType) {
		this(pieceType, new InitialState());
	}

	@Override
	public boolean canLeap() {
		return this.pieceType.canLeap();
	}

	@Override
	public boolean canMove(final Position sourcePosition, final Position targetPosition) {
		validate(sourcePosition, targetPosition);

		if (this.pieceType.canMove(sourcePosition, targetPosition, pieceState.getPawnMovableRange())) {
			this.pieceState = pieceState.shiftNextState();
			return true;
		}
		return false;
	}

	@Override
	public boolean canCatch(final Position sourcePosition, final Position targetPosition) {
		validate(sourcePosition, targetPosition);

		if (this.pieceType.canCatch(sourcePosition, targetPosition)) {
			this.pieceState = pieceState.shiftNextState();
			return true;
		}
		return false;
	}

	private void validate(final Position sourcePosition, final Position targetPosition) {
		Objects.requireNonNull(sourcePosition, "소스 위치가 null입니다.");
		Objects.requireNonNull(targetPosition, "타겟 위치가 null입니다.");
	}

	public boolean isSamePieceColor(final ChessPiece chessPiece) {
		Objects.requireNonNull(chessPiece, "체스 피스가 null입니다.");
		return this.pieceType.isSamePieceColor(chessPiece.pieceType);
	}

	public boolean isSame(final PieceColor pieceColor) {
		Objects.requireNonNull(pieceColor, "체스 색상이 null입니다.");
		return this.pieceType.isSame(pieceColor);
	}

	public boolean isPawn() {
		return this.pieceType.isPawn();
	}

	public boolean isKing() {
		return this.pieceType.isKing();
	}

	public String getName() {
		return this.pieceType.getName();
	}

	public double getScore() {
		return this.pieceType.getScore();
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		final ChessPiece that = (ChessPiece)object;
		return this.pieceType.equals(that.pieceType);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pieceType);
	}

}
