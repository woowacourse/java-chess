package chess.domain.piece.pawn;

import chess.domain.board.position.Vertical;
import chess.domain.direction.Direction;
import chess.domain.piece.Owner;

public class WhitePawn extends Pawn {

    private static final WhitePawn WHITE_PAWN = new WhitePawn();

    private WhitePawn() {
        super(Owner.WHITE, Direction.whitePawnDirections());
    }

    public static WhitePawn getInstance() {
        return WHITE_PAWN;
    }

    @Override
    public boolean isFirstLine(final Vertical vertical) {
        return Vertical.TWO.equals(vertical);
    }

    @Override
    public String getSymbol() {
        return "p";
    }
}
