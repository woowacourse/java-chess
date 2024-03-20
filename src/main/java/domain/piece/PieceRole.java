package domain.piece;

import domain.position.Position;

public interface PieceRole {

    boolean canMove(Position sourcePosition, Position targetPosition);
}
