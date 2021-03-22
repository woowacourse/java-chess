package chess.domain.piece.pawn;

import chess.domain.board.position.Horizontal;
import chess.domain.direction.Direction;
import chess.domain.piece.Owner;

public class BlackPawn extends Pawn{

    private static final BlackPawn BLACK_PAWN = new BlackPawn();

    public BlackPawn() {
        super(Owner.BLACK, Direction.blackPawnDirections());
    }

    public static BlackPawn getInstance() {
        return BLACK_PAWN;
    }

    @Override
    public boolean isFirstLine(final Horizontal horizontal) {
        return Horizontal.SEVEN.equals(horizontal);
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}
