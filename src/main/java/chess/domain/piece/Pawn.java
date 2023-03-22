package chess.domain.piece;

import chess.domain.board.BoardSnapShot;
import chess.domain.board.Square;
import chess.domain.piece.strategy.DirectStrategy;
import chess.domain.piece.strategy.vector.DirectVector;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(final Color color) {
        super(color, new DirectStrategy(DirectVector.ofPawnByColor(color)));
    }

    @Override
    public boolean isMovable(final Square source, final Square destination, final BoardSnapShot boardSnapShot) {
        final List<Square> route = strategy.findRoute(source, destination);
        return boardSnapShot.canMovePawn(source, route);
    }
}
