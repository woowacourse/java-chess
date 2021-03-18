package chess.domain.board.piece;

import chess.domain.board.Position;
import chess.domain.board.Square;

public class Knight extends Piece{
    public Knight(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Position source, Position target, boolean hasEnemy) {
        int horizontalDifferent = source.getHorizontalDistance(target);
        int verticalDifferent = source.getVerticalDistance(target);

        if(!((horizontalDifferent == 2 && verticalDifferent == 1) || (horizontalDifferent ==1 && verticalDifferent ==2))){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "N";
    }
}
