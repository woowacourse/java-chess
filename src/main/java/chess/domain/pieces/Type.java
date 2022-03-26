package chess.domain.pieces;

import chess.domain.position.Position;

public interface Type {

    boolean isMovable(Position source, Position target);

    boolean isPawn();

    boolean isKing();

    double score();

    String symbol();
}
