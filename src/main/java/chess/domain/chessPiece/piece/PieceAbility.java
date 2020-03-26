package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.movetype.MoveType;

public interface PieceAbility {
	boolean isMovable(MoveType moveType);

	String pieceName();

	boolean isEqualPosition(Position position);

	double getScore();
}
