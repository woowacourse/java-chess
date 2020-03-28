package chess.domain.chessPiece.pieceState;

import chess.domain.chessPiece.Catchable;
import chess.domain.chessPiece.Movable;
import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

public interface PieceState extends Movable, Catchable {

	boolean canLeap();

	boolean canMove(Position sourcePosition, Position targetPosition);

	boolean canCatch(Position sourcePosition, Position targetPosition);

	PieceState shiftNextState(PieceColor pieceColor);

}
