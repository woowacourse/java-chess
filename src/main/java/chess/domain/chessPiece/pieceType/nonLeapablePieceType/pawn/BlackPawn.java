package chess.domain.chessPiece.pieceType.nonLeapablePieceType.pawn;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.MoveDirection;

public class BlackPawn extends Pawn {

	public BlackPawn(final PieceColor pieceColor, final PieceState pieceState) {
		super(pieceColor, pieceState);

		validate();
		movableDirections.addAll(MoveDirection.getBlackPawnMovableDirections());
		catchableDirections.addAll(MoveDirection.getBlackPawnCatchableDirections());
	}

	public BlackPawn(final PieceState pieceState) {
		this(PieceColor.BLACK, pieceState);
	}

	public BlackPawn() {
		this(new InitialState());
	}

	private void validate() {
		if (!pieceColor.isBlack()) {
			throw new IllegalArgumentException("유효하지 않은 색상입니다.");
		}
	}

}
