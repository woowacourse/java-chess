package chess.domain.piece;

import chess.domain.board.BoardSnapShot;
import chess.domain.board.Square;
import chess.domain.piece.strategy.SlidingStrategy;
import chess.domain.piece.strategy.vector.SlidingVector;
import java.util.List;

public class Bishop extends Piece {

    public Bishop(final Color color) {
        super(color, new SlidingStrategy(SlidingVector.ofBishop()));
    }

    @Override
    public boolean isMovable(final Square source, final List<Square> route, final BoardSnapShot boardSnapShot) {
        return boardSnapShot.canMove(source, route);
    }
}
