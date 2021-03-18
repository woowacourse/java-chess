package domain.piece;

import domain.position.Position;

import java.util.List;

public interface Piece {
    void move(Position to, Pieces pieces);

    void kill(Position to, Pieces pieces);

    String display();

    boolean hasPosition(Position position);

    Position getPosition();
}
