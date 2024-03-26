package domain.piece.piecerole;

import domain.position.Position;

public interface PieceRole {

    boolean canMove(Position sourcePosition, Position targetPosition);

    boolean isPawn();

    boolean isSlidingPiece();
}
