package chess.domain.pieces;

import chess.domain.position.Position;

public interface Role {

    String getSymbol();

    boolean isMovable(Position source, Position target);

    boolean isPawn();

    boolean isKing();

    double score();
}
