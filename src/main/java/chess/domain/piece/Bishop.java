package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.board.position.Position;

public class Bishop extends Piece {

    private static final int ABLE_DISTANCE_TO_MOVE = 7;

    private static final Bishop BLACK_BISHOP = new Bishop(Owner.BLACK);
    private static final Bishop WHITE_BISHOP = new Bishop(Owner.WHITE);

    public static Bishop getInstanceOf(Owner owner){
        if (owner.equals(Owner.BLACK)){
            return BLACK_BISHOP;
        }

        if (owner.equals(Owner.WHITE)){
            return WHITE_BISHOP;
        }

        throw new IllegalArgumentException("Invalid Bishop");
    }

    public Bishop(Owner owner) {
        super(owner, Direction.diagonalDirections());
    }

    @Override
    public boolean validateMove(Position source, Position target, Piece targetPiece) {
        return true;
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}
