package chess.piece.directional.normal.longrange;

import chess.board.Position;
import chess.piece.Direction;
import chess.piece.Piece;
import chess.piece.Side;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends LongRangePiece {

    public Queen(final Position position, final Side side) {
        super(position, side);
        this.directions = initDirections();
    }

    @Override
    protected List<Direction> initDirections() {
        return new ArrayList<>(Arrays.asList(Direction.values()));
    }

    @Override
    public Piece move(Position positionToMove) {
        return new Queen(positionToMove, this.side);
    }
}
