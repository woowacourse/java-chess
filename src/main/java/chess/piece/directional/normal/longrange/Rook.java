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
        return new ArrayList<>(
                List.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT)
        );
    }

    @Override
    public Piece move(Position positionToMove) {
        return new Rook(positionToMove, this.side);
    }
}
