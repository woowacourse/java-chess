package chess.domain.chessPiece.pieceType.nonLeapablePieceType;

import chess.domain.chessPiece.pieceState.InitialState;
import chess.domain.chessPiece.pieceState.PieceState;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.MoveDirection;
import chess.domain.position.Position;

public class Rook extends NonLeapableChessPiece {

	public static final String NAME = "R";
	private static final int SCORE = 5;

	public Rook(PieceColor pieceColor, PieceState pieceState) {
		super(pieceColor, pieceState);

		movableDirections.addAll(MoveDirection.getRookMovableDirections());
	}

	public Rook(final PieceColor pieceColor) {
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
