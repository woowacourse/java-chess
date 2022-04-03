package chess.domain.piece.role;

import chess.domain.position.Position;

public interface Role {

    String getSymbol();

    void checkMovable(Position source, Position target);

    boolean isPawn();

    boolean isKing();

    double score();
}
