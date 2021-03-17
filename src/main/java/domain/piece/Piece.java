package domain.piece;

import domain.position.Position;

import java.util.List;

public interface Piece {
    void move(Position to, List<Position> pieces);

    String display();

    boolean hasPosition(Position position);
}
