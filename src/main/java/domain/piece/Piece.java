package domain.piece;

import domain.position.Position;

import java.util.List;

public interface Piece {
    void move(Position to, List<Piece> pieces);

    void kill(Position to, List<Piece> pieces);

    String display();

    boolean hasPosition(Position position);

    Position getPosition();
}
