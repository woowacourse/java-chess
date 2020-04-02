package chess.domain.chessPiece.pieceType.nonLeapablePieceType;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

public class Queen extends NonLeapableChessPiece {

	public static final String NAME = "Q";
	private static final int SCORE = 9;

	public Queen(PieceColor pieceColor, PieceState pieceState) {
		super(pieceColor, pieceState);

		movableDirections.addAll(MoveDirection.getQueenMovableDirections());
	}

	public Queen(final PieceColor pieceColor) {
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
