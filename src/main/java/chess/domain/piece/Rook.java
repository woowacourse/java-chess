package chess.domain.piece;

import chess.domain.board.BoardSnapShot;
import chess.domain.board.Square;
import chess.domain.piece.strategy.SlidingStrategy;
import chess.domain.piece.strategy.vector.SlidingVector;
import java.util.List;

public class Rook extends Piece {

    public Rook(final Color color) {
        super(color, new SlidingStrategy(SlidingVector.ofRook()));
    }

    @Override
    public boolean isMovable(final Square source, final List<Square> route, final BoardSnapShot boardSnapShot) {
        return boardSnapShot.canMove(source, route);
    }
}
