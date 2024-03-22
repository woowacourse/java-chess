package domain.piece;

import domain.position.Position;

public interface Piece {
    void validate(Position resource, Position target, Piece other);

    Color color();

    Type type();
}
