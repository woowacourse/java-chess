package chess.domain.piece;

import chess.domain.position.MovableAreaFactory;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Position position, Team team) {
        super(position, Name.QUEEN, team);
    }

    @Override
    public void canPawnMove(Piece that) {
        throw new IllegalAccessError();
    }

    @Override
    protected boolean isNotMovableTo(Position start, Position destination) {
        List<Position> movable = new ArrayList<>();
        movable.addAll(MovableAreaFactory.columnOf(start));
        movable.addAll(MovableAreaFactory.rowOf(start));
        movable.addAll(MovableAreaFactory.diagonalsOf(start));

        return !movable.contains(destination);
    }
}
