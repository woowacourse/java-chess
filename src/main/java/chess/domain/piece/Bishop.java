package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Collection;

public class Bishop extends Piece {

    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    protected Piece createNewPiece(Position to) {
        return new Bishop(getColor(), to);
    }

    @Override
    protected boolean isPossibleMovement(Position to, Collection<Piece> pieces) {
        return getPosition().isDiagonalWay(to);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }
}
