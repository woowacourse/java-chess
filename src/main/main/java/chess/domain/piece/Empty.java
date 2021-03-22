package chess.domain.piece;

import chess.domain.piece.rule.Direction;
import chess.domain.board.position.Position;

import java.util.List;

public class Empty extends Piece {
    private static final int ABLE_DISTANCE_TO_MOVE = 1;

    private static final Empty EMPTY = new Empty();

    private Empty() {
        super(Owner.NONE);
    }

    public static Empty getInstance() {
        return EMPTY;
    }

    @Override
    public boolean validateMove(Position source, Position target, Piece targetPiece) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score score() {
        return null;
    }

    @Override
    public String getSymbol() {
        return ".";
    }

    @Override
    public List<Direction> getDirections() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}
