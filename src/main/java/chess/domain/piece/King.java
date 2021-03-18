package chess.domain.piece;

import chess.domain.board.position.Position;

public class King extends Piece{
    private static final King BLACK_KING = new King(Owner.WHITE);
    private static final King WHITE_KING = new King(Owner.BLACK);

    private static final int ABLE_DISTANCE_TO_MOVE = 1;

    public static King getInstanceOf(Owner owner){
        if (owner.equals(Owner.BLACK)){
            return BLACK_KING;
        }

        if (owner.equals(Owner.WHITE)){
            return WHITE_KING;
        }

        throw new IllegalArgumentException("Invalid pawn");
    }

    public King(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Position source, Position target, Piece targetPiece) {
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
