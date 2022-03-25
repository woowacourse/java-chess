package chess.domain.pieces;

import chess.domain.position.Position;

public interface Type {

    String getSymbol();

    boolean isMovable(Position source, Position target);

    boolean isPawn();

    boolean isKing();

    double score();
}
