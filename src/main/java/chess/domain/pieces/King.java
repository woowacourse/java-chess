package chess.domain.pieces;

import chess.domain.position.Position;

public class King implements Type {

    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return false;
    }
}
