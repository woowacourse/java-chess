package chess.piece.pawn;

import chess.Position;
import chess.piece.Color;
import chess.piece.Direction;
import chess.piece.Piece;
import java.util.Set;

public final class FirstBlackPawn extends Pawn {

    public FirstBlackPawn(final Position position) {
        super(Color.BLACK, position);
    }

    @Override
    protected Set<Direction> directions() {
        return Direction.ofFirstBlackPawn();
    }

    @Override
    protected Piece update(final Position destination) {
        return new BlackPawn(destination);
    }
}
