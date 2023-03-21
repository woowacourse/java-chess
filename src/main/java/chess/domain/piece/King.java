package chess.domain.piece;

import chess.domain.board.BoardSnapShot;
import chess.domain.board.Square;
import chess.domain.piece.strategy.DirectStrategy;
import chess.domain.piece.strategy.vector.DirectVector;
import java.util.List;

public class King extends Piece {

    public King(final Color color) {
        super(color, new DirectStrategy(DirectVector.ofKing()));
    }

    @Override
    public boolean isMovable(final Square source, final List<Square> route, final BoardSnapShot boardSnapShot) {
        return boardSnapShot.canMove(source, route);
    }
}
