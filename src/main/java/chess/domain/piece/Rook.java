package chess.domain.piece;

import chess.domain.board.BoardSnapShot;
import chess.domain.board.Square;
import chess.domain.piece.strategy.SlidingStrategy;
import chess.domain.piece.strategy.vector.SlidingVector;
import java.util.List;

public class Rook extends Piece {

    private static final double SCORE = 5;

    public Rook(final Color color) {
        super(color, new SlidingStrategy(SlidingVector.ofRook()), SCORE);
    }

    @Override
    public boolean isMovable(final Square source, final Square destination, final BoardSnapShot boardSnapShot) {
        final List<Square> route = strategy.findRoute(source, destination);
        return boardSnapShot.canMove(source, route);
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
