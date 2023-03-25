package chess.piece.nonsliding;

import chess.board.Position;
import chess.piece.Piece;
import chess.piece.Side;
import java.util.Collections;
import java.util.List;

public abstract class NonSlidingPiece extends Piece {

    public NonSlidingPiece(final Position position, final Side side) {
        super(position, side);
    }

    @Override
    public List<Position> getPaths(Position targetPosition) {
        return Collections.emptyList();
    }

    @Override
    public boolean canPassThrough() {
        return true;
    }
}
