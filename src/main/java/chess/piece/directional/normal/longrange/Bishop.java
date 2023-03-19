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
        return new ArrayList<>(
                List.of(Direction.UP_RIGHT, Direction.UP_LEFT, Direction.DOWN_RIGHT, Direction.DOWN_LEFT)
        );
    }

    @Override
    public Piece move(Position positionToMove) {
        return new Bishop(positionToMove, this.side);
    }
}
