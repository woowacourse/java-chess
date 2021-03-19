package chess.domain.piece;

import chess.domain.position.Position;

public interface Piece {
    void move(Position to, Pieces pieces);

    void kill(Position to, Pieces pieces);

    String display();

    boolean hasPosition(Position position);

    Position getPosition();

    boolean isSameColor(Color color);

    boolean isEmpty();
}
