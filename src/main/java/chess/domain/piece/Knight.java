package chess.domain.piece;

import chess.domain.path.Direction;
import chess.domain.path.MovablePaths;
import chess.domain.path.Path;
import chess.domain.Position;
import chess.domain.TeamColor;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final List<Direction> DIRECTIONS;

    static {
        DIRECTIONS = List.of(Direction.EAST_DOWN, Direction.EAST_UP, Direction.SOUTH_LEFT,
            Direction.SOUTH_RIGHT, Direction.WEST_DOWN, Direction.WEST_UP, Direction.NORTH_LEFT,
            Direction.NORTH_RIGHT);
    }

    public Knight(final TeamColor color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public MovablePaths findMovablePaths(final Position current) {
        List<Path> paths = new ArrayList<>();
        for (Direction direction : DIRECTIONS) {
            paths.add(Path.ofSinglePath(current, direction));
        }
        return new MovablePaths(paths);
    }

    @Override
    public boolean isPawn() {
        return false;
    }

}
