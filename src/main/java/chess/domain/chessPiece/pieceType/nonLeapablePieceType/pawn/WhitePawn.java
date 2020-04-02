package chess.domain.chessPiece.pieceType.nonLeapablePieceType.pawn;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.MoveDirection;

public class WhitePawn extends Pawn {

	public WhitePawn(final PieceColor pieceColor, final PieceState pieceState) {
		super(pieceColor, pieceState);

		validate();
		movableDirections.addAll(MoveDirection.getWhitePawnMovableDirections());
		catchableDirections.addAll(MoveDirection.getWhitePawnCatchableDirections());
	}

	public WhitePawn(final PieceState pieceState) {
		this(PieceColor.WHITE, pieceState);
	}

	public WhitePawn() {
		this(new InitialState());
	}

	private void validate() {
		if (!pieceColor.isWhite()) {
			throw new IllegalArgumentException("유효하지 않은 색상입니다.");
		}
	}

}
