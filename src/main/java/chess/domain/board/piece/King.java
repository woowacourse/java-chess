package chess.domain.board.piece;

import chess.domain.board.Position;
import chess.domain.board.Square;

public class King extends Piece{
    private static final int ABLE_DISTANCE_TO_MOVE = 1;
    public King(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Square source, Square target) {
        if(source.getDistance(target) != ABLE_DISTANCE_TO_MOVE){
            throw new IllegalArgumentException();
        }

        if(!(source.isStraight(target) || source.isDiagonal(target))){
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Score score() {
        return new Score(0);
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
