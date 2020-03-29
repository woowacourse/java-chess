package chess.domain.piece;

import chess.domain.position.MovableAreaFactory;
import chess.domain.position.Position;

public class Bishop extends Piece {
    public Bishop(Position position, Team team) {
        super(position, Name.BISHOP, team);
    }

    @Override
    public void canPawnMove(Piece that) {
        throw new IllegalAccessError();
    }

    @Override
    protected boolean isNotMovableTo(Position start, Position destination) {
        return !MovableAreaFactory.diagonalsOf(start).contains(destination);
    }
}
