package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.movepattern.MovePattern;

public interface PieceAbility {
	boolean isMovable(MovePattern movePattern);

	String pieceName();

	boolean isEqualPosition(Position position);

	boolean isBlackTeam();

	boolean isWhiteTeam();

	double getScore();
}
