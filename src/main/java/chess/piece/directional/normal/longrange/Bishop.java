package chess.piece.directional.normal.longrange;

import chess.board.Position;
import chess.piece.Direction;
import chess.piece.Piece;
import chess.piece.Side;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends LongRangePiece {

    public Bishop(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    @Override
    protected List<Direction> initDirections() {
        final List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UP_RIGHT);
        directions.add(Direction.UP_LEFT);
        directions.add(Direction.DOWN_RIGHT);
        directions.add(Direction.DOWN_LEFT);
        return directions;
    }

    @Override
    public Piece move(Position positionToMove) {
        return new Bishop(positionToMove, this.side);
    }
}
