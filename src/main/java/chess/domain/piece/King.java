package chess.domain.piece;

import chess.domain.board.position.Position;

public class King extends Piece{
    private static final int ABLE_DISTANCE_TO_MOVE = 1;
    public King(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Position source, Position target, boolean hasEnemy) {
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
