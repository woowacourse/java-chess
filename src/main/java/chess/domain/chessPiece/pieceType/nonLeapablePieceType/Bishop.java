package chess.domain.chessPiece.pieceType.nonLeapablePieceType;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

public class Bishop extends NonLeapableChessPiece {

	public static final String NAME = "B";
	private static final int SCORE = 3;

	public Bishop(PieceColor pieceColor, PieceState pieceState) {
		super(pieceColor, pieceState);

		movableDirections.addAll(MoveDirection.getBishopMovableDirections());
	}

	public Bishop(final PieceColor pieceColor) {
		this(pieceColor, new InitialState());
	}

	@Override
	protected boolean canMoveRange(final Position sourcePosition, final Position targetPosition) {
		return true;
	}

	@Override
	public String getName() {
		return pieceColor.convertFrom(NAME);
	}

	@Override
	public double getScore() {
		return SCORE;
	}

}
