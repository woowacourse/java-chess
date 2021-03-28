package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;

public interface Piece {
    void moveToEmpty(final Position to, final Pieces pieces);

    void moveForKill(final Position to, final Pieces pieces);

    String display();

    boolean hasPosition(final Position position);

    Position getPosition();

    boolean isSameColor(final Color color);

    boolean isEmpty();

    boolean isKing();

    double score();

    boolean isPawn();

    Column getColumn();
}
