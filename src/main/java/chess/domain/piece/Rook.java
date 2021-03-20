package chess.domain.piece;

import chess.domain.direction.Direction;
import chess.domain.board.position.Position;

import java.util.Arrays;
import java.util.List;

public class Rook extends Piece{

    private static final int ABLE_DISTANCE_TO_MOVE = 7;
    private static final Rook BLACK_ROOK = new Rook(Owner.BLACK);
    private static final Rook WHITE_ROOK = new Rook(Owner.WHITE);

    public Rook(Owner owner) {
        super(owner, Direction.straightDirections());
    }

    public static Rook getInstanceOf(Owner owner){
        if (owner.equals(Owner.BLACK)){
            return BLACK_ROOK;
        }

        if (owner.equals(Owner.WHITE)){
            return WHITE_ROOK;
        }

        throw new IllegalArgumentException("Invalid Rook");
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
        return "R";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}
