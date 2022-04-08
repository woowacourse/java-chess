package chess.domain.piece;

import static chess.domain.board.Direction.EAST;
import static chess.domain.board.Direction.NORTH;
import static chess.domain.board.Direction.NORTH_EAST;
import static chess.domain.board.Direction.NORTH_WEST;
import static chess.domain.board.Direction.SOUTH;
import static chess.domain.board.Direction.SOUTH_EAST;
import static chess.domain.board.Direction.SOUTH_WEST;
import static chess.domain.board.Direction.WEST;

import chess.domain.board.Direction;
import java.util.List;

public final class Queen extends AbstractStraightPiece {

    private static final List<Direction> DIRECTIONS = List
            .of(NORTH, SOUTH, EAST, WEST, NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST);
    private static final double POINT = 9.0;
    private static final PieceType PIECE_TYPE = PieceType.QUEEN;

    public Queen(Color color) {
        super(color, DIRECTIONS, PIECE_TYPE);
    }

    @Override
    public double getPoint() {
        return POINT;
    }

    @Override
    public String getOutput() {
        String output = PIECE_TYPE.getOutput();
        if (color == Color.WHITE) {
            return output.toLowerCase();
        }
        return output;
    }
}
