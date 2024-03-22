package domain.piece;

import domain.position.Position;

// todo 공통 처리 추상화
public interface Piece {
    void validate(Position resource, Position target, Piece other);

    Color getColor();

    Type getType();
}
