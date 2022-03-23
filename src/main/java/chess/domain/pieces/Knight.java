package chess.domain.pieces;

import chess.domain.position.Position;

public class Knight implements Type {

    @Override
    public String getSymbol() {
        return "N";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
