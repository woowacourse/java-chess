package chess.domain.pieces;

import chess.domain.position.Position;

public interface Type {

    boolean isMovable(final Position source, final Position target);

    boolean isPawn();

    boolean isKing();

    double score();

    Symbol symbol();
}
