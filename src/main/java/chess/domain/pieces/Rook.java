package chess.domain.pieces;

import chess.domain.position.Position;

public class Rook implements Type {

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return source.isStraight(target);
    }
}
