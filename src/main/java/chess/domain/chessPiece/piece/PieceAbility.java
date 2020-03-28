package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.movepattern.MovePattern;

public interface PieceAbility {
	void validateMovePattern(MovePattern movePattern, Piece targetPiece);

	void move(Position position);

	boolean isEqualPosition(Position position);

	boolean isBlackTeam();

	boolean isWhiteTeam();

	double getScore();

	boolean isKnight();

	String getPieceName();
}
