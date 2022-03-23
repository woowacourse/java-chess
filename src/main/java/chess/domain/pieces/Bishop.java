package chess.domain.pieces;

import chess.domain.position.Position;

public class Bishop implements Type {

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
