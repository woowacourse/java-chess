package chess.model.piece;

import chess.model.Color;
import chess.model.Direction;
import chess.model.Square;
import java.util.List;

public final class Rook extends LineMovablePiece {

    private static final String ROOK_NAME = "r";

    public Rook(Color color, Square square) {
        super(color, square);
    }

    @Override
    public Point getPoint() {
        return Point.ROOK;
    }

    @Override
    public String getLetter() {
        return ROOK_NAME;
    }

    @Override
    protected List<Direction> direction() {
        return List.of(Direction.EAST, Direction.NORTH, Direction.SOUTH, Direction.WEST);
    }
}
