package chess.domain.pieces;

import chess.domain.position.Position;

public class Rook implements Type {

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean isMovable(Position source, Position target) {
        return isVertical(source, target) || isHorizontal(source, target);
    }

    private boolean isVertical(Position source, Position target) {
        return source.isVertical(target);
    }

    private boolean isHorizontal(Position source, Position target) {
        return source.isHorizontal(target);
    }
}
