package chess.domain.piece;

import chess.domain.position.MovableAreaFactory;
import chess.domain.position.Position;

public class Knight extends Piece {
    public Knight(Position position) {
        super(position, Name.KNIGHT);
    }

    @Override
    protected boolean isNotMovableTo(Position start, Position destination) {
        return !MovableAreaFactory.knightOf(start).contains(destination);
    }
}
