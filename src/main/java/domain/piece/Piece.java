package domain.piece;

import domain.position.Position;

public interface Piece {
    void validateMovement(Position source, Position target, Piece other);

    Color color();

    Type type();
}
