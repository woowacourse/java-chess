package chess.domain.pieces;

import chess.domain.position.Position;

public final class Pawn implements Type {

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
