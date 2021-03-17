package domain.piece;

import domain.position.Position;

public interface Piece {
    boolean canMove(Position from, Position to);

    String display();
}
