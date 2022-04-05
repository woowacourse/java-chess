package chess.domain.piece.moving;

import chess.domain.position.Position;

public interface MovingPattern {

    boolean canMove(Position source, Position target, boolean hasTargetPiece);
}
