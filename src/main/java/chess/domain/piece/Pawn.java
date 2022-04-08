package chess.domain.piece;

import static chess.domain.board.Direction.NORTH;
import static chess.domain.board.Direction.NORTH_EAST;
import static chess.domain.board.Direction.NORTH_WEST;
import static chess.domain.board.Direction.SOUTH;
import static chess.domain.board.Direction.SOUTH_EAST;
import static chess.domain.board.Direction.SOUTH_WEST;

import chess.domain.board.Direction;
import chess.domain.board.Rank;
import java.util.List;

public final class Pawn extends AbstractPawnPiece {

    private static final double POINT = 1.0;
    private static final List<Direction> BLACK_PAWN_DIRECTIONS = List.of(SOUTH, SOUTH_EAST, SOUTH_WEST);
    private static final List<Direction> WHITE_PAWN_DIRECTIONS = List.of(NORTH, NORTH_EAST, NORTH_WEST);
    private static final String output = "P";

    private final Rank startRank;

    Pawn(Color color, List<Direction> directions, Rank startRank) {
        super(color, directions);
        this.startRank = startRank;
    }

    public static Pawn of(Color color) {
        return new Pawn(color, getDirections(color), Rank.getStartRankOfPawn(color));
    }

    private static List<Direction> getDirections(Color color) {
        if (color == Color.BLACK) {
            return BLACK_PAWN_DIRECTIONS;
        }
        return WHITE_PAWN_DIRECTIONS;
    }

    @Override
    public double getPoint() {
        return POINT;
    }

    @Override
    public String getOutput() {
        if (color == Color.WHITE) {
            return output.toLowerCase();
        }
        return output;
    }

    @Override
    Rank getStartRow() {
        return startRank;
    }
}
