package chess.domain.movepattern;

import chess.domain.chessPiece.position.Position;

public interface PatternChecker {
	boolean isCrossPattern(Position source, Position target);

	boolean isKnightPattern(Position source, Position target);

	boolean isStraightPattern(Position source, Position target);
}
