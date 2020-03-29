package chess.domain.piece;

import chess.domain.position.MovableAreaFactory;
import chess.domain.position.Position;

public class Knight extends Piece {
    public Knight(Position position, Team team) {
        super(position, Name.KNIGHT, team);
    }

    @Override
    public void canPawnMove(Piece that) {
        throw new IllegalAccessError();
    }

    @Override
    protected boolean isNotMovableTo(Position start, Position destination) {
        return !MovableAreaFactory.knightOf(start).contains(destination);
    }
}
