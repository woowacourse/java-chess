package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;

public interface Piece {
    void moveToEmpty(Position to, Pieces pieces);

    void moveForKill(Position to, Pieces pieces);

    String display();

    boolean hasPosition(Position position);

    Position getPosition();

    boolean isSameColor(Color color);

    boolean isEmpty();

    boolean isKing();

    double score();

    boolean isPawn();

    Column getColumn();
}
