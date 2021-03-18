package chess.domain.board.piece;

import chess.domain.board.Position;
import chess.domain.board.Square;

public class Queen extends Piece{

    public Queen(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Position source, Position target, boolean hasEnemy) {
        if(!(source.isStraight(target) || source.isDiagonal(target))){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
