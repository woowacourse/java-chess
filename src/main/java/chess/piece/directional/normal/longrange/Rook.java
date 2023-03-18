package chess.piece.directional.normal.longrange;

import chess.board.Position;
import chess.piece.Direction;
import chess.piece.Piece;
import chess.piece.Side;

import java.util.ArrayList;
import java.util.List;

public class Rook extends LongRangePiece {

    public Rook(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    @Override
    protected List<Direction> initDirections() {
        final List<Direction> directions = new ArrayList<>();
        directions.add(Direction.UP);
        directions.add(Direction.DOWN);
        directions.add(Direction.LEFT);
        directions.add(Direction.RIGHT);
        return directions;
    }

    @Override
    public Piece move(Position positionToMove) {
        return new Rook(positionToMove, this.side);
    }
}
