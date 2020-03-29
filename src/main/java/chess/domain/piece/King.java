package chess.domain.piece;

import chess.domain.position.MovableAreaFactory;
import chess.domain.position.Position;

public class King extends Piece {
    public King(Position position) {
        super(position, Name.KING);
    }

    @Override
    protected boolean isNotMovableTo(Position start, Position destination) {
        return !MovableAreaFactory.kingOf(start).contains(destination);
    }
}