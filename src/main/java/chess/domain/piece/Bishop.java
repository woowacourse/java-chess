package chess.domain.piece;

import chess.domain.board.position.Position;

public class Bishop extends Piece {
    private static final Bishop BLACK_BISHOP = new Bishop(Owner.WHITE);
    private static final Bishop WHITE_BISHOP = new Bishop(Owner.BLACK);

    private static final int ABLE_DISTANCE_TO_MOVE = 1;

    public static Bishop getInstanceOf(Owner owner){
        if (owner.equals(Owner.BLACK)){
            return BLACK_BISHOP;
        }

        if (owner.equals(Owner.WHITE)){
            return WHITE_BISHOP;
        }

        throw new IllegalArgumentException("Invalid pawn");
    }

    public Bishop(Owner owner) {
        super(owner);
    }

    @Override
    public void validateMove(Position source, Position target, Piece targetPiece) {
        if(!source.isDiagonal(target)) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
