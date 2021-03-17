package chess.domain.board.piece;

import chess.domain.board.Position;

public class Pawn extends Piece{
    private static final Pawn BLACK_PAWN = new Pawn(Owner.BLACK);
    private static final Pawn WHITE_PAWN = new Pawn(Owner.WHITE);

    private Pawn(Owner owner) {
        super(owner);
    }

    public static Pawn getInstanceOf(Owner owner){
        if (owner.equals(Owner.BLACK)){
            return BLACK_PAWN;
        }

        if (owner.equals(Owner.WHITE)){
            return WHITE_PAWN;
        }

        throw new IllegalArgumentException("Invalid pawn");
    }

    @Override
    public boolean isValidMove(Position source, Position target) {
        return false;
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
