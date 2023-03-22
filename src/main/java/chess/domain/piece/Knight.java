package chess.domain.piece;

import chess.domain.board.BoardSnapShot;
import chess.domain.board.Square;
import chess.domain.piece.strategy.DirectStrategy;
import chess.domain.piece.strategy.vector.DirectVector;
import java.util.List;

public class Knight extends Piece {

    private static final double SCORE = 2.5;

    public Knight(final Color color) {
        super(color, new DirectStrategy(DirectVector.ofKnight()), SCORE);
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
