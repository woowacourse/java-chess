package chess.domain.chessPiece.piece;

import chess.domain.Position;
import chess.domain.movefactory.MoveType;

public interface PieceAbility {
	boolean isMovable(MoveType moveType);

	String pieceName();

	boolean isEqualPosition(Position position);

	double getScore();
}
