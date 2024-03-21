package domain.piece;

import domain.position.Position;

public interface Piece {
    void validateMovement(Position resource, Position target, Piece other);

    Color getColor();
}
